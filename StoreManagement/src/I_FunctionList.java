public interface I_FunctionList {
    
    void add(); // Add e new vehicle.
    
    int find(String code); // Find Index.

    void delete(); // Delete by vehicle id.

    void update(); // Update by vehicle id..

    void output(); // Show all and show descending by price.
  
    void writeFile(); // Save file txt. 
    
    void loadFile(); // Load file txt. 
}