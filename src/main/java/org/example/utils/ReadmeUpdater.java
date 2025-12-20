package org.example.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * README.md 自动更新工具
 * <p>
 * 功能：
 * 1. 统计题目完成进度（通过分析源码中已实现的方法）
 * 2. 更新 README.md 中的进度徽章
 * 3. 更新题目列表的完成状态
 * <p>
 * 使用方式：
 * mvn exec:java -Dexec.mainClass="org.example.utils.ReadmeUpdater"
 *
 * @author Algorithm Practice
 * @since 2025-12-09
 */
public class ReadmeUpdater {

    /**
     * 项目根目录
     */
    private static final Path PROJECT_ROOT = Paths.get(System.getProperty("user.dir"));

    /**
     * README文件路径
     */
    private static final Path README_PATH = PROJECT_ROOT.resolve("README.md");

    /**
     * 题目清单目录路径
     */
    private static final Path PROBLEMS_LIST_DIR = PROJECT_ROOT.resolve("src/main/resources/problemsList");

    /**
     * 日期格式化器
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 日期正则模式 (匹配 yyyy-MM-dd 格式)
     */
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

    /**
     * 进度统计数据
     */
    private static class ProgressStats {
        String fileName;
        int totalProblems = 0;
        int completedProblems = 0;

        int easyTotal = 0;
        int easyCompleted = 0;

        int mediumTotal = 0;
        int mediumCompleted = 0;
        int hardTotal = 0;
        int hardCompleted = 0;

        public ProgressStats() {}

        public ProgressStats(String fileName) {
            this.fileName = fileName;
        }

        /**
         * 计算完成百分比
         */
        int getCompletionPercentage() {
            return totalProblems == 0 ? 0 : (completedProblems * 100) / totalProblems;
        }

        /**
         * 获取进度颜色（根据完成百分比）
         */
        String getProgressColor() {
            int percentage = getCompletionPercentage();
            if (percentage < 30) return "red";
            if (percentage < 60) return "orange";
            if (percentage < 90) return "yellow";
            return "brightgreen";
        }

        /**
         * 生成进度徽章
         */
        String generateProgressBadge() {
            return String.format("![总数](https://img.shields.io/badge/完成进度-%d%%2F%d-%s)",
                    completedProblems, totalProblems, getProgressColor());
        }

        /**
         * 生成难度徽章（单行格式）
         */
        String generateDifficultyBadges() {
            return String.format("""
                            ![简单](https://img.shields.io/badge/简单-%d%%2F%d-green)
                            ![中等](https://img.shields.io/badge/中等-%d%%2F%d-orange)
                            ![困难](https://img.shields.io/badge/困难-%d%%2F%d-red)""",
                    easyCompleted, easyTotal, mediumCompleted, mediumTotal, hardCompleted, hardTotal);
        }

        /**
         * 合并统计数据
         */
        void merge(ProgressStats other) {
            this.totalProblems += other.totalProblems;
            this.completedProblems += other.completedProblems;
            this.easyTotal += other.easyTotal;
            this.easyCompleted += other.easyCompleted;
            this.mediumTotal += other.mediumTotal;
            this.mediumCompleted += other.mediumCompleted;
            this.hardTotal += other.hardTotal;
            this.hardCompleted += other.hardCompleted;
        }
    }

    /**
     * 主函数
     */
    public static void main(String[] args) {
        try {
            System.out.println("=== README 更新工具 ===\n");

            ReadmeUpdater updater = new ReadmeUpdater();
            updater.updateReadme();

            System.out.println("\n✅ README 更新完成！");
        } catch (Exception e) {
            System.err.println("❌ 更新失败: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * 更新README
     */
    public void updateReadme() throws IOException {
        // 1. 获取所有markdown文件
        System.out.println("📁 扫描题目清单目录...");
        List<Path> markdownFiles = findMarkdownFiles();
        System.out.println("找到 " + markdownFiles.size() + " 个题目清单文件\n");

        // 2. 处理每个markdown文件
        ProgressStats totalStats = new ProgressStats();
        for (Path mdFile : markdownFiles) {
            System.out.println("📊 处理文件: " + mdFile.getFileName());
            ProgressStats fileStats = analyzeMarkdownFile(mdFile);
            printStats(fileStats);
            
            // 更新该文件的进度徽章
            updateMarkdownFileBadges(mdFile, fileStats);
            
            // 累加到总统计
            totalStats.merge(fileStats);
            System.out.println();
        }

        // 3. 更新主README
        System.out.println("📝 更新主 README.md...");
        printStats(totalStats);
        updateReadmeProgress(totalStats);
    }

    /**
     * 查找所有markdown文件
     */
    private List<Path> findMarkdownFiles() throws IOException {
        List<Path> mdFiles = new ArrayList<>();
        if (!Files.exists(PROBLEMS_LIST_DIR)) {
            System.out.println("⚠️  题目清单目录不存在: " + PROBLEMS_LIST_DIR);
            return mdFiles;
        }

        Files.walk(PROBLEMS_LIST_DIR, 1)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().toLowerCase().endsWith(".md"))
                .forEach(mdFiles::add);

        return mdFiles;
    }

    /**
     * 分析单个markdown文件
     */
    private ProgressStats analyzeMarkdownFile(Path mdFile) throws IOException {
        ProgressStats stats = new ProgressStats(mdFile.getFileName().toString());
        String content = Files.readString(mdFile);
        String[] lines = content.split("\n");
        
        List<String> updatedLines = new ArrayList<>();
        boolean inProblemList = false;
        boolean fileModified = false;

        for (String line : lines) {
            String processedLine = line;
            
            // 检测"题目列表"章节
            if (line.trim().equals("## 📋 题目列表")) {
                inProblemList = true;
                updatedLines.add(processedLine);
                continue;
            }

            // 遇到下一个二级标题，退出题目列表区域
            if (inProblemList && line.trim().startsWith("## ") && !line.contains("题目列表")) {
                inProblemList = false;
            }

            // 在题目列表区域内，解析和处理条目
            if (inProblemList && line.trim().startsWith("- [")) {
                parseProblemLine(line, stats);
                
                // 检查是否需要添加日期
                String lineWithDate = addDateIfNeeded(line);
                if (!lineWithDate.equals(line)) {
                    processedLine = lineWithDate;
                    fileModified = true;
                }
            }
            
            updatedLines.add(processedLine);
        }

        // 如果文件被修改，写回文件
        if (fileModified) {
            String updatedContent = String.join("\n", updatedLines);
            Files.writeString(mdFile, updatedContent);
            System.out.println("  ✓ 已添加完成日期");
        }

        return stats;
    }

    /**
     * 如果需要，在已完成题目后添加日期
     * 
     * @param line 题目行
     * @return 可能添加了日期的行
     */
    private String addDateIfNeeded(String line) {
        String trimmed = line.trim();
        
        // 检查是否已完成 [x] 或 [X]
        if (!trimmed.startsWith("- [x]") && !trimmed.startsWith("- [X]")) {
            return line;
        }
        
        // 检查是否已经有日期
        Matcher dateMatcher = DATE_PATTERN.matcher(line);
        if (dateMatcher.find()) {
            return line; // 已经有日期，不修改
        }
        
        // 添加当前日期
        String currentDate = LocalDate.now().format(DATE_FORMATTER);
        String cleanLine = line.trim().replaceAll("\\r?\\n$", ""); // 移除行尾换行符
        return cleanLine + " - (" + currentDate + ")";
    }

    /**
     * 解析题目行
     * 例如: "- [ ] 合并两个有序数组 ![简单](https://img.shields.io/badge/-简单-green)"
     */
    private void parseProblemLine(String line, ProgressStats stats) {
        String trimmed = line.trim();
        
        // 判断是否完成: [x] 或 [X] 表示完成, [ ] 表示未完成
        boolean completed = trimmed.startsWith("- [x]") || trimmed.startsWith("- [X]");
        
        // 判断难度 - 优先匹配徽章中的中文标识
        boolean isDifficultyFound = false;
        
        if (line.contains("-简单-") || (line.contains("简单") && line.contains("green"))) {
            stats.easyTotal++;
            if (completed) stats.easyCompleted++;
            isDifficultyFound = true;
        } else if (line.contains("-中等-") || (line.contains("中等") && line.contains("orange"))) {
            stats.mediumTotal++;
            if (completed) stats.mediumCompleted++;
            isDifficultyFound = true;
        } else if (line.contains("-困难-") || (line.contains("困难") && line.contains("red"))) {
            stats.hardTotal++;
            if (completed) stats.hardCompleted++;
            isDifficultyFound = true;
        }

        // 如果找到了难度标识,计入总数
        if (isDifficultyFound) {
            stats.totalProblems++;
            if (completed) {
                stats.completedProblems++;
            }
        }
    }

    /**
     * 更新markdown文件的徽章
     */
    private void updateMarkdownFileBadges(Path mdFile, ProgressStats stats) throws IOException {
        String content = Files.readString(mdFile);
        String[] lines = content.split("\n", -1);
        StringBuilder newContent = new StringBuilder();

        boolean badgesUpdated = false;
        int lineIndex = 0;

        while (lineIndex < lines.length) {
            String line = lines[lineIndex];

            // 找到第一个标题行后的徽章区域
            if (!badgesUpdated && line.startsWith("# ") && lineIndex + 1 < lines.length) {
                newContent.append(line).append("\n");
                lineIndex++;

                // 跳过空行
                while (lineIndex < lines.length && lines[lineIndex].trim().isEmpty()) {
                    lineIndex++;
                }

                // 跳过旧的徽章行
                while (lineIndex < lines.length && lines[lineIndex].trim().startsWith("![")) {
                    lineIndex++;
                }

                // 插入新的徽章
                newContent.append("\n");
                newContent.append(stats.generateProgressBadge()).append("\n");
                newContent.append(stats.generateDifficultyBadges()).append("\n");
                badgesUpdated = true;
                continue;
            }

            newContent.append(line);
            if (lineIndex < lines.length - 1) {
                newContent.append("\n");
            }
            lineIndex++;
        }

        Files.writeString(mdFile, newContent.toString());
        System.out.println("  ✓ 徽章已更新");
    }

    /**
     * 打印统计信息
     */
    private void printStats(ProgressStats stats) {
        if (stats.fileName != null) {
            System.out.println("  文件: " + stats.fileName);
        }
        System.out.println("  总题数: " + stats.totalProblems);
        System.out.println("  已完成: " + stats.completedProblems);
        System.out.println("  完成率: " + stats.getCompletionPercentage() + "%");
        System.out.println("  难度分布:");
        System.out.printf("    简单: %d/%d%n", stats.easyCompleted, stats.easyTotal);
        System.out.printf("    中等: %d/%d%n", stats.mediumCompleted, stats.mediumTotal);
        System.out.printf("    困难: %d/%d%n", stats.hardCompleted, stats.hardTotal);
    }

    /**
     * 更新README中的进度信息
     */
    private void updateReadmeProgress(ProgressStats stats) throws IOException {
        if (!Files.exists(README_PATH)) {
            throw new IOException("README.md 文件不存在: " + README_PATH);
        }

        // 读取README内容
        String content = Files.readString(README_PATH);

        // 构建新的进度块
        String newProgressBlock = buildProgressBlock(stats);

        // 替换进度块（在 <!-- PROGRESS_START --> 和 <!-- PROGRESS_END --> 之间）
        Pattern pattern = Pattern.compile(
                "<!-- PROGRESS_START -->.*?<!-- PROGRESS_END -->",
                Pattern.DOTALL
        );

        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            content = matcher.replaceFirst(newProgressBlock);
        } else {
            System.out.println("⚠️  未找到进度标记，请确保README中包含 <!-- PROGRESS_START --> 和 <!-- PROGRESS_END --> 标记");
            return;
        }

        // 写回文件
        Files.writeString(README_PATH, content);
        System.out.println("✓ README 进度已更新");
    }

    /**
     * 构建进度块内容
     */
    private String buildProgressBlock(ProgressStats stats) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!-- PROGRESS_START -->\n");
        sb.append(stats.generateProgressBadge()).append("\n");
        sb.append(stats.generateDifficultyBadges()).append("\n");
        sb.append("<!-- PROGRESS_END -->");
        return sb.toString();
    }
}
