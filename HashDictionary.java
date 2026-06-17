import java.util.LinkedList;

public class HashDictionary implements DictionaryADT {
    private LinkedList<Data>[] table;
    private int numRecords;
    private int tableSize;

    // Constructor initializes empty dictionary of the specified size
    public HashDictionary(int size) {
        this.tableSize = size;
        this.table = (LinkedList<Data>[]) new LinkedList[size];
        this.numRecords = 0;
        
        // Initialize each bucket with empty LinkedList
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Hash function
    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (101 * hash + key.charAt(i)) % tableSize;
        }
        return hash;
    }

    // Adds record to dictionary
    public int put(Data record) throws DictionaryException {
        String config = record.getConfiguration();
        int index = hash(config);
        
        // Check if config already exists
        for (Data data : table[index]) {
            if (data.getConfiguration().equals(config)) {
                throw new DictionaryException();
            }
        }
        
        // Add new record
        table[index].add(record);
        numRecords++;
        
        // Return 1 if there was a collision, 0 otherwise
        return table[index].size() > 1 ? 1 : 0;
    }

    // Removes record with given config from dictionary
    public void remove(String config) throws DictionaryException {
        int index = hash(config);
        LinkedList<Data> bucket = table[index];
        
        for (Data data : bucket) {
            if (data.getConfiguration().equals(config)) {
                bucket.remove(data);
                numRecords--;
                return;
            }
        }
        
        throw new DictionaryException();
    }

    // Returns score for the given config, or -1 if not found
    public int get(String config) {
        int index = hash(config);
        
        for (Data data : table[index]) {
            if (data.getConfiguration().equals(config)) {
                return data.getScore();
            }
        }
        
        return -1;
    }

    // Returns number of records in the dictionary
    public int numRecords() {
        return numRecords;
    }
}
