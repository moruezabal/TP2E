package tp2e;

import java.util.ArrayList;

public class Tree {

	private Integer value;
	private Tree left;
	private Tree right;

	public Tree(Integer value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	public void add(Integer newValue) {
		if (this.value == null)
			this.value = newValue;
		else {
			if (this.value > newValue) {
				if (this.left == null)
					this.left = new Tree(newValue);
				else
					this.left.add(newValue);
			} else if (this.value < newValue) {
				if (this.right == null)
					this.right = new Tree(newValue);
				else
					this.right.add(newValue);
			}
		}
	}
	
	public int getRoot() {
		return this.value;
	}
	
	public boolean hasElem(int elem) {
		
		if (this.value == null)
			return false;
		else {
			if(this.value == elem)
				return true;
			else if(this.value > elem) {
				if(this.left != null) {
					return this.left.hasElem(elem);
				}
				else {
					return false;
				}	
			}
			else {
				if(this.right != null) {
					return this.right.hasElem(elem);
				}
				else {
					return false;
				}
			}
		}
	}
	
	public Tree findTree(int value) {
		Tree arbolito = null;
        if (!this.isEmpty()) {
            if (value == this.value)
            	return this;
            else {
                if (value < this.value) {
                	if (this.left !=null)
                		arbolito = this.left.findTree(value);
                }
                    
                else 
                	if (this.right !=null)
                		arbolito = this.right.findTree(value);
            }
        }
        return arbolito;
	}
	
	public boolean isEmpty() {
		return this.value == null;
	}
	
	public boolean delete(int value) {
		return deleteNode(this, value) != null;
	}
	
	private static Tree deleteNode(Tree root, int value) {
        if (root == null)
            return null;
        if (root.value > value) {
            root.left = deleteNode(root.left, value);
        } else if (root.value < value) {
            root.right = deleteNode(root.right, value);
 
        } else {
            // if nodeToBeDeleted have both children
            if (root.left != null && root.right != null) {
                Tree temp = root;
                // Finding minimum element from right
                Tree minNodeForRight = minimumElement(temp.right);
                // Replacing current node with minimum node from right subtree
                root.value = minNodeForRight.value;
                // Deleting minimum node from right now
                root.right = deleteNode(root.right, minNodeForRight.value);
 
            }
            // if nodeToBeDeleted has only left child
            else if (root.left != null) {
                root = root.left;
            }
            // if nodeToBeDeleted has only right child
            else if (root.right != null) {
                root = root.right;
            }
            // if nodeToBeDeleted do not have child (Leaf node)
            else
                root = null;
        }
        return root;
	 }
	 
	 public static Tree minimumElement(Tree root) {
        if (root.left == null)
            return root;
        else {
            return minimumElement(root.left);
        }
     }

	
	
	private Tree findPredecessor() {
		if (this.right == null) 
			return this;
		else
			return this.right.findPredecessor();
	}

	public int getHeight() {
		if (this.isEmpty()) 
            return 0;
        else {
        	if(this.left != null && this.right != null)
        		return (1 + Math.max((this.left.getHeight()), (this.right.getHeight())));
        	else if (this.left != null) 
        		return (1 + this.left.getHeight());
        	else if (this.right != null) 
        		return (1 + this.right.getHeight());
        	else
        		return 1;
        }
	}
	
	public void printPosOrder() {
		if(!isEmpty()) {
			if(this.left != null)
				this.left.printPosOrder();
			if(this.right != null)
				this.right.printPosOrder();
			System.out.print(this.value + " ");
		}
	}
	
	public void printPreOrder() {
		if(!isEmpty()) {
			System.out.print(this.value + " ");
			if(this.left != null)
				this.left.printPosOrder();
			if(this.right != null)
				this.right.printPosOrder();	
		}
	}
	
	public void printInOrder() {
		if(!isEmpty()) {
			if(this.left != null)
				this.left.printPosOrder();
			System.out.print(this.value + " ");
			if(this.right != null)
				this.right.printPosOrder();	
		}
	}
	
	public ArrayList<Integer> getLongestBranch() {
		return this.getLongestBranch(new ArrayList<Integer>());
	}
	
	private ArrayList<Integer> getLongestBranch(ArrayList<Integer> largest) {
		//TO DO
		return largest;
	}
	
	public ArrayList<Integer> getFrontera() {
		return getFrontera(new ArrayList<Integer>());
	}
	
	private ArrayList<Integer> getFrontera(ArrayList<Integer> leaves) {
		if(!this.isEmpty()) {
			if(this.isLeaf())
				leaves.add(this.value);
			else if (this.left != null && this.right != null){
				ArrayList<Integer> leavesLeft = this.left.getFrontera(new ArrayList<Integer>());
				ArrayList<Integer> leavesRight = this.right.getFrontera(new ArrayList<Integer>());
				leavesLeft.addAll(leavesRight);
				leaves.addAll(leavesLeft);
			} else if (this.left != null) {
				ArrayList<Integer> leavesLeft = this.left.getFrontera(new ArrayList<Integer>());
				leaves.addAll(leavesLeft);
			} else {
				ArrayList<Integer> leavesRight = this.right.getFrontera(new ArrayList<Integer>());
				leaves.addAll(leavesRight);
			}
		}
		return leaves;
	}
	
	private boolean isLeaf() {
	        boolean leaf = false;
	        if( this.left == null && this.right == null) {
	            leaf = true;
	        }
	        return leaf;
	    }
	
	public int getMaxElem() {
		if(this.right != null) {
			return this.right.getMaxElem();
		}
		return this.value;
	}
	
	public ArrayList<Integer> getElemAtLevel(int level) {
		return getElemAtLevel(level, new ArrayList<Integer>());
	}
	
	private ArrayList<Integer> getElemAtLevel(int level, ArrayList<Integer> elements){
		
		if (!this.isEmpty()) {
			if (level == 0) {
				elements.add(this.value);
			}
			else {
				if(this.left != null && this.right != null) {
					this.left.getElemAtLevel(level-1, elements);
					this.right.getElemAtLevel(level-1, elements);
				}
				else if(this.left != null) {
					this.left.getElemAtLevel(level-1, elements);
				}
				else {
					this.right.getElemAtLevel(level-1, elements);
				}
			}	
		}
		return elements;
	}
	
	

}
