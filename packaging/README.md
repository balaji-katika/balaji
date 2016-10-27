The solution is implemented in two phases. Its a hybrid model based on FFDH algorithm described at http://cgi.csc.liv.ac.uk/~epa/surveyhtml.html <br>
Phase 1 : <br>
&nbsp;&nbsp;&nbsp;&nbsp;Step 1: Sort the list of items in the descending order of volumes<br>
&nbsp;&nbsp;&nbsp;&nbsp;Step 2: For each item in the sorted list, pick the smallest container that can accomodate the given item.<br>
&nbsp;&nbsp;&nbsp;&nbsp;Step 3: If no existing container can accomodate the item, provision a new container that can accomodate the item. <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The container should be of minimal size that can accomodate the given item <br>
&nbsp;&nbsp;&nbsp;&nbsp;Step 4: Push the item into the level in the container that can accomodate (FFDH algo) this item<br>
    <br>
&nbsp;&nbsp;&nbsp;&nbsp;Minimum number of containers = sum of sizes of each container<br>
    
Phase 2:<br>
&nbsp;&nbsp;&nbsp;&nbsp;Step 1: Sort the containers in the ascending order of their size<br>
&nbsp;&nbsp;&nbsp;&nbsp;Step 2: For each container, check if it has enough room to accomodate its previous members (if not already accomodated) <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Discount the min number of containers each time the a container accomodates a smaller one into it.<br>
&nbsp;&nbsp;&nbsp;&nbsp;Step 3: Repeat step 2 until all the containers are checked<br>


Execute the below command to run build the jar<br>
&nbsp;&nbsp;&nbsp;&nbsp;mvn clean install<br>

Refer to <br>com.walmart.packaging.driver.Solution</b> for the driver program. Sample input file is located at <b>com.walmart.packaging.utils.TestData</b>
