//package halamish.reem.remember.activity_main;
//
//import java.util.Iterator;
//
//import lombok.Getter;
//
///**
// * Created by Re'em on 5/17/2017.
// */
//
//public class OopTree {
//    private static class Node { // those "@Getter" generate automatic get() functions
//        @Getter Node parent;
//        @Getter Node right;
//        @Getter Node left;
//        @Getter int data;
//    }
//
//    public static class TreeIterator implements Iterator<Integer> {
//        Node nextNodeToTakeOut;
//        public TreeIterator(Node rootNode) {
//            // start by starting nextNodeToTakeOut to look at the minimum node in the tree
//            nextNodeToTakeOut = rootNode;
//            while (nextNodeToTakeOut.getLeft() != null) {
//                nextNodeToTakeOut = nextNodeToTakeOut.getLeft();
//            }
//        }
//
//        @Override
//        public boolean hasNext() {
//            return nextNodeToTakeOut != null;
//        }
//
//        /**
//         * using loop invariant -
//         * all nodes SMALLER THAN nextNodeToTakeOut has already been called.
//         * so now we look for the minimum bigger node to look forward to
//         *
//         * so, taking nextNodeToTakeOut.getLeft() won't help as it's SMALLER than
//         * nextNodeToTakeOut, and by the loop invariant we already went there.
//         *
//         * question is, is getRight() bigger than getParent()?
//         *
//         * answer is, if nextNodeToTakeOut is a leftNode of its parent, then
//         * nextNodeToTakeOut.getRight() will be smaller than nextNodeToTakeOut.getParent()
//         * (because a right subtree of a left child of yours will still be smaller than you!)
//         *
//         * and if nextNodeToTakeOut is a rightNode of its parent,
//         * then the parent is smaller then nextNodeToTakeOut so by the loop invariant
//         * we already visited that parent node so we don't need to go there again onceoever
//         *
//         * so basically first visit right child if possible, than parent.
//         *
//         * @return
//         */
//        @Override
//        public Integer next() {
//            int returnValue = nextNodeToTakeOut.getData();
//            if (nextNodeToTakeOut.getRight() != null) {
//                nextNodeToTakeOut = nextNodeToTakeOut.getRight();
//                while (nextNodeToTakeOut.getLeft() != null) {
//                    // to keep the loop invariant WE NEED TO FIND THE SMALLEST BIGGER NUMBER,
//                    // so go left as most as we can
//                    nextNodeToTakeOut = nextNodeToTakeOut.getLeft();
//                }
//            } else {
//                // no right-child, so this sub-tree is finished. go to the parent
//                // when going up to the parent, if nextNodeToTakeOut was a right child of the parent,
//                // so parent is a smaller node than nextNodeToTakeOut, than by the loop invariant
//                // we go to a node we already been to, so continue going up until finding a parent
//                // that nextNodeToTakeOut is a left child of it
//                //
//
//                Node parent = nextNodeToTakeOut.getParent();
//                while (parent != null && nextNodeToTakeOut == parent.getRight()) {
//                    nextNodeToTakeOut = parent;
//                    parent = nextNodeToTakeOut.getParent();
//                }
//                // reached here? means nextNodeToTakeOut is it's parent right child. so that parent
//                // (which is bigger then nextNodeToTakeOut, and the smallest bigger node)
//                // will be the next node to look at
//                nextNodeToTakeOut = nextNodeToTakeOut.getParent();
//
//                // notice that when nextNodeToTakeOut will look at the root node, that ^ line will
//                // cause it to terminate
//
//            }
//
//            return returnValue;
//        }
//
//        /*
//
//        execution example for the tree
//                  5
//              3      13
//            1  4    9   17
//
//        first the constructor will look at 1.
//        calling next() will save (1) to be returned. 1 has no right children, so it will go up
//               to the parent. 1 is a left child of his parent so the while() won't even start.
//               so we ending up looking at 3 and will return 1
//        calling next() will save (3) to be returned. 3 has right child - 4 - so we go there.
//                no left children for 4 so we look at 4 and return 3.
//        calling next() will save (4) to be returned.  4 has no right children, so it will go up
//               to the parent. 4 is a left child of his parent so the while() will make us look at 3
//               and then stop (cause 3 is NOT a left child of his parent )
//               then we look at the parent of 3 (which is 5) and return 4
//        calling next() will save (5) to be returned. 5 has right child - 13 - so we will look at 13
//                13 has left child - 9 - so we will look at 9. 9 has no left child so we stop there
//                and return 5
//        calling next() will save (9) to be returned. 9 has no right child so we go to the parent.
//                9 is not a right child of it's parent so the while won't start. now we look at the
//                parent - 13 - (knowing well that 9 is a left child of it) and return 9.
//        calling next() will save (13) to be returned. 13 has a right child - 17 - so we look at it.
//                17 has no left child so the while won't even start and we return 13.
//        calling next will save (17) to be returned. 17 has no right child so we go to the parent.
//                the while will make us look at his parent 13, (which 17 is a right-child of)
//                and then look at his parent 5 (which 13 is a right child of) and then stop
//                because parent of 5 will be null
//                then we take one another step and look at null and finished.
//
//       we went through 1 --> 3 --> 4 --> 5 --> 9 --> 13 --> 17 as requested
//
//
//         */
//    }
//
//
//}
