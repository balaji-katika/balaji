The solution is implemented in two phases. Its a hybrid model based on FFDH algorithm described at http://cgi.csc.liv.ac.uk/~epa/surveyhtml.html
Phase 1 : 
    Step 1: Sort the list of items in the descending order of volumes
    Step 2: For each item in the sorted list, pick the smallest container that can accomodate the given item.
    Step 3: If no existing container can accomodate the item, provision a new container that can accomodate the item. 
            The container should be of minimal size that can accomodate the given item
    Step 4: Push the item into the level in the container that can accomodate (FFDH algo) this item
    
    Minimum number of containers = sum of sizes of each container
    
Phase 2:
    Step 1: Sort the containers in the ascending order of their size
    Step 2: For each container, check if it has enough room to accomodate its previous members (if not already accomodated)
            Discount the min number of containers each time the a container accomodates a smaller one into it.
    Step 3: Repeat step 2 until all the containers are checked


