package moviecatalog.view.tree;

import javax.swing.event.TreeModelEvent;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by Cornelius on 03.04.2015.
 */
public class MovieTreeModelListener implements javax.swing.event.TreeModelListener{

    public void treeNodesChanged(TreeModelEvent e) {
        DefaultMutableTreeNode node;
        node = (DefaultMutableTreeNode)
                (e.getTreePath().getLastPathComponent());

        try {
            int index = e.getChildIndices()[0];
            node = (DefaultMutableTreeNode)(node.getChildAt(index));
        } catch (NullPointerException exc) {}

        System.out.println("The user has finished editing the node.");
        System.out.println("New value: " + node.getUserObject());
    }
    public void treeNodesInserted(TreeModelEvent e) {
    }
    public void treeNodesRemoved(TreeModelEvent e) {
    }
    public void treeStructureChanged(TreeModelEvent e) {
    }

}
