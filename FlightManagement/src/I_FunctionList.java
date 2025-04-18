public interface I_FunctionList {
    
    
    int find(String code); // Find Index.

    void add(); // Add e new vehicle.

    void delete(); // Delete by vehicle id.

    void update(); // Update by vehicle id.

    void search(); // Search by vehicle name (descending) and search by vehicle id.
  
    void readFile(); // Read file txt.
  
    void writeFile(); // Save file txt. 
    
    void loadFile(); // Load file txt. 
}