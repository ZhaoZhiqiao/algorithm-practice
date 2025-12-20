package org.example.dataStructure;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 四叉树节点定义
 */
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class QuadTreeNode {
    public boolean val;
    public boolean isLeaf;
    public QuadTreeNode topLeft;
    public QuadTreeNode topRight;
    public QuadTreeNode bottomLeft;
    public QuadTreeNode bottomRight;

    public QuadTreeNode(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
}
