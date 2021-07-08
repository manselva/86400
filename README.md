# 86400

Have used Java as the coding language. Automated the Ebay Web Browser and Petstore Functional automation.

Created the framework using the BDD cucumber framework. Test Report created is a simple cucumber report which can be found under the target/site folder after execution.

Features folder will contain both the UI automation and Rest Api fucntional test scenarios.

For Rest API test, tried to get the available Pet IDs uing findByStatus API and used the Pet ID for update, find and delete operations.

For UI scenario its a simple, search product, add to cart and remove it from there scenario.

For execution we can use the command line maven command mvn test. Havent added the envionment variables, URL is passed through the CSV file. Code can be extended to suit the pipeline structure.

Git Hub Url # https://github.com/manselva/86400.git

Execution steps,

1) Clone or the download the zip folder extract it and open it using Intelij or Eclipse.
2) In command line do a mvn clean compile
3) mvn test
