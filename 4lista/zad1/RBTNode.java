public class RBTNode {
    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    RBTNode p;
    String key;
    boolean color;
    RBTNode left;
    RBTNode right;

    public RBTNode(RBTNode parent, String key) {
        this.key = key;
        this.p = parent;
        this.color = RED;
        this.left = null;
        this.right = null;
    }

    public RBTNode(String key) {
        this.key = key;
        this.p = null;
        this.color = BLACK;
        this.left = null;
        this.right = null;
    }

    private void rotateLeft(RBTree tree, RBTNode node) {
        RBTNode rightNode = node.right;
        node.right = rightNode.left;
        if(node.right != null) {
            node.right.p = node;
        }
        rightNode.p = node.p;
        if(node.p == null) {
            tree.setRoot(rightNode);
        } else if(node == node.p.left) {
            node.p.left = rightNode;
        } else {
            node.p.right = rightNode;
        }
        rightNode.left = node;
        node.p = rightNode;
    }

    private void rotateRight(RBTree tree, RBTNode node) {
        RBTNode leftNode = node.left;
        node.left = leftNode.right;
        if(node.left != null) {
            node.left.p = node;
        }
        leftNode.p = node.p;
        if(node.p == null) {
            tree.setRoot(leftNode);
        } else if(node == node.p.left) {
            node.p.left = leftNode;
        } else {
            node.p.right = leftNode;
        }
        leftNode.right = node;
        node.p = leftNode;
    }

    private RBTNode insertion(String key) {
        if(key.compareTo(this.key) < 0) {
            if(left != null) {
                return left.insertion(key);
            } else {
                left = new RBTNode(this, key);
                return left;
            }
        } else {
            if(right != null) {
                return right.insertion(key);
            } else {
                right = new RBTNode(this, key);
                return right;
            }
        }
    }

    private void fixInsert(RBTree tree, RBTNode newNode) {
        RBTNode parent = null;
        RBTNode gParent = null;

        while(newNode.p != null && newNode.color != BLACK && newNode.p.color == RED) {
            parent = newNode.p;
            gParent = parent.p;

            if(parent == gParent.left) {
                RBTNode uncle = gParent.right;
                if(uncle != null && uncle.color == RED) {
                    gParent.color = RED;
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    newNode = gParent;
                } else {
                    if(newNode == parent.right) {
                        rotateLeft(tree, parent);
                        newNode = parent;
                        parent = newNode.p;
                    }
                    rotateRight(tree, gParent);
                    swapColor(parent, gParent);
                    newNode = parent;
                }
            } else {
                RBTNode uncle = gParent.left;
                if(uncle != null && uncle.color == RED) {
                    gParent.color = RED;
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    newNode = gParent;
                } else {
                    if(newNode == parent.left) {
                        rotateRight(tree, parent);
                        newNode = parent;
                        parent = newNode.p;
                    }
                    rotateLeft(tree, gParent);
                    swapColor(parent, gParent);
                    newNode = parent;
                }
            }
        }
        tree.root.color = BLACK;
        //set root color black in RBTree class
    }

    private void swapColor(RBTNode n1, RBTNode n2) {
        boolean tmpColor = n1.color;
        n1.color = n2.color;
        n2.color= tmpColor;
    }

    public void insert(RBTree tree, String key) {
        RBTNode newNode = insertion(key);
        fixInsert(tree, newNode);
    }

    // public void deleteKey(RBTree tree, String key) {
    //     tree.root = deleteRec(tree.root, key);
    // }

    // public RBTNode deleteRec(RBTNode root, String key) {
    //     if(root == null) {
    //         return root;
    //     }
    //     if(key.compareTo(root.key) < 0) {
    //         root.left = deleteRec(root.left, key);
    //     } else if(key.compareTo(root.key) > 0) {
    //         root.right = deleteRec(root.right, key);
    //     } else {
    //         if(root.left == null) {
    //             return root.right;
    //         } else if(root.right == null) {
    //             return root.left;
    //         } else {
    //             root.key = root.right.min();
    //             root.right = deleteRec(root.right, root.key);
    //         }
    //     }
    //     return root;
    // }

    public void delete(RBTree tree, String key) {
        RBTNode toDelete = this.find(key);
        if(toDelete.p != null) {
            if(toDelete.left == null) {
                if(toDelete.p.left == toDelete) {
                    toDelete.p.left = toDelete.right;
                    if(toDelete.right != null) {
                        toDelete.right.p = toDelete.p;
                    }
                } else {
                    toDelete.p.right = toDelete.right;
                    if(toDelete.right != null) {
                        toDelete.right.p = toDelete.p;
                    }
                }
                if( (toDelete.right != null && toDelete.right.color == RED) || toDelete.color == RED ) {
                    if(toDelete.right != null) {
                        toDelete.right.color = BLACK;
                    }
                } else {
                    fixExtraBlack(tree, toDelete.right);
                }
            } else if(toDelete.right == null) {
                if(toDelete.p.left == toDelete) {
                    toDelete.p.left = toDelete.left;
                    toDelete.left.p = toDelete.p;
                } else {
                    toDelete.p.right = toDelete.left;
                    toDelete.left.p = toDelete.p;
                }
                if(toDelete.color == RED || toDelete.left.color == RED) {
                    toDelete.left.color = BLACK;
                } else {
                    fixExtraBlack(tree, toDelete.left);
                }
            } else {
                if(toDelete.right.left == null) {
                    if(toDelete.p.left == toDelete) {
                        toDelete.p.left = toDelete.right;
                        toDelete.right.p = toDelete.p;
                        toDelete.right.left = toDelete.left;
                        toDelete.left.p = toDelete.right;
                    } else {
                        toDelete.p.right = toDelete.right;
                        toDelete.right.p = toDelete.p;
                        toDelete.right.left = toDelete.left;
                        toDelete.left.p = toDelete.right;
                    }
                    if(toDelete.color == BLACK) {
                        fixExtraBlack(tree, toDelete.right);
                    }
                } else {
                    RBTNode successor = toDelete.right;
                    while(successor.left != null) {
                        successor = successor.left;
                    }
                    successor.p.left = successor.right;
                    if(successor.right != null) {
                        successor.right.p = successor.p;
                    }
                    if(toDelete.p.left == toDelete) {
                        toDelete.p.left = successor;
                        successor.p = toDelete.p;
                        successor.left = toDelete.left;
                        toDelete.left.p = successor;
                        successor.right = toDelete.right;
                        toDelete.right.p = successor;
                    }
                    if(toDelete.color == BLACK) {
                        fixExtraBlack(tree, successor);
                    }
                }
            }
        } else {
            if(toDelete.left == null) {
                toDelete.right.p = null;
                toDelete.right.color = BLACK;
                tree.setRoot(toDelete.right);
                // if(toDelete.color == RED || toDelete.right.color == RED) {
                //     toDelete.right.color = BLACK;
                // } else {
                //     fixExtraBlack(tree, toDelete.right);
                // }
            } else if(toDelete.right == null) {
                toDelete.left.p = null;
                toDelete.left.color = BLACK;
                tree.setRoot(toDelete.left);
                // if(toDelete.color == RED || toDelete.left.color == RED) {
                //     toDelete.left.color = BLACK;
                // } else {
                //     fixExtraBlack(tree, toDelete.left);
                // }
            } else {
                if(toDelete.right.left == null) {
                        toDelete.right.p = null;
                        toDelete.right.color = BLACK;
                        toDelete.right.left = toDelete.left;
                        toDelete.left.p = toDelete.right;
                    // if(toDelete.color == BLACK) {
                    //     fixExtraBlack(tree, toDelete.right);
                    // }
                } else {
                    RBTNode successor = toDelete.right;
                    while(successor.left != null) {
                        successor = successor.left;
                    }
                    successor.p.left = successor.right;
                    if(successor.right != null) {
                        successor.right.p = successor.p;
                    }
                    successor.p = null;
                    successor.left = toDelete.left;
                    toDelete.left.p = successor;
                    successor.right = toDelete.right;
                    toDelete.right.p = successor;
                    tree.setRoot(successor);
                    // if(toDelete.color == BLACK) {
                    //     fixExtraBlack(tree, successor);
                    // }
                }
            }
        }
    }

    public void fixExtraBlack(RBTree tree, RBTNode node) {
        if(node == null) {
            return;
        }
        if(node.p == null) {
            node.color = BLACK;
        } else {
            RBTNode sibling;
            if(node.p.left == node) {
                sibling = node.p.right;
                    if(sibling == null) {
                        return;
                    }
                if(sibling.color == RED) {
                    rotateLeft(tree, node.p);
                    sibling.color = BLACK;
                    node.p.color = RED;
                    fixExtraBlack(tree, node);
                } else if(sibling.left.color == RED) {
                    rotateRight(tree, sibling);
                    rotateLeft(tree, node.p);
                    node.p.p.color = node.p.color;
                    node.p.p.left.color = BLACK;
                    node.p.p.right.color = BLACK;
                } else if(sibling.right.color == RED) {
                    rotateLeft(tree, node.p);
                    node.p.p.color = node.p.color;
                    node.p.p.left.color = BLACK;
                    node.p.p.right.color = BLACK;
                } else {
                    sibling.color = RED;
                    if(sibling.p.color == RED) {
                        sibling.p.color = BLACK;
                    } else {
                        fixExtraBlack(tree, node.p);
                    }
                }
            } else {
                sibling = node.p.left;
                if(sibling == null) {
                    return;
                }
                if(sibling.color == RED) {
                    rotateRight(tree, node.p);
                    sibling.color = BLACK;
                    node.p.color = RED;
                    fixExtraBlack(tree, node);
                } else if(sibling.left.color == RED) {
                    rotateRight(tree, node.p);
                    node.p.p.color = node.p.color;
                    node.p.p.left.color = BLACK;
                    node.p.p.right.color = BLACK;
                } else if(sibling.right.color == RED) {
                    rotateLeft(tree, sibling);
                    rotateRight(tree, node.p);
                    node.p.p.color = node.p.color;
                    node.p.p.left.color = BLACK;
                    node.p.p.right.color = BLACK;
                } else {
                    sibling.color = RED;
                    if(sibling.p.color == RED) {
                        sibling.p.color = BLACK;
                    } else {
                        fixExtraBlack(tree, node.p);
                    }
                }
            }

        }
    }

    public RBTNode find(String key) {
        if(key.compareTo(this.key) < 0) {
            if(this.left != null) {
                return this.left.find(key);
            } else {
                return null;
            }
        } else if(key.compareTo(this.key) > 0) {
            if(this.right != null) {
                return this.right.find(key);
            } else {
                return null;
            }
        } else {
            return this;
        }
    }

    public String min() {
        if(this.left != null) {
            return this.left.min();
        } else {
            return this.key;
        }
    }

    public String max() {
        if(this.right != null) {
            return this.right.max();
        } else {
            return this.key;
        }
    }

    public String successor(String key) {
        RBTNode keyNode = find(key);
        if(keyNode == null) {
            return null;
        }
        RBTNode successorNode = keyNode.right;
        if(successorNode != null) {
            while(successorNode.left != null) {
                successorNode = successorNode.left;
            }
        } else {
            successorNode = keyNode;
            if(successorNode.p == null) {
                return null;
            }
            while(successorNode.p.left != successorNode) {
                successorNode = successorNode.p;
                if(successorNode.p == null) {
                    return null;
                }
            }
        }
        return successorNode.key;
    }

    public String inorder(String s) {
        if(this.left != null) {
            s = this.left.inorder(s);
        }
        s = s + this.key + " ";
        if(this.right != null) {
            s = this.right.inorder(s);
        }
        return s;
    }

    public boolean checkColors() {
        // System.err.println(this.color);
        boolean left;
        boolean right;
        if(this.color == RED) {
            if(this.left != null) {
                if(this.left.color == RED) {
                    left = false;
                } else {
                    left = this.left.checkColors(); 
                }
            } else {
                left = true;
            }
            if(this.right != null) {
                if(this.right.color == RED) {
                    right = false;
                } else {
                    right = this.right.checkColors();
                }
            } else {
                right = true;
            }
        } else {
            if(this.left != null) {
                left = this.left.checkColors();
            } else {
                left = true;
            }
            if(this.right != null) {
                right = this.right.checkColors();
            } else {
                right = true;
            }
        }
        if(left == true && right == true) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        int left = 0;
        int right = 0;

        if(this.left != null) {
            left = this.left.size();
        }
        if(this.right != null) {
            right = this.right.size();
        }
        return left + right + 1;
    }
}