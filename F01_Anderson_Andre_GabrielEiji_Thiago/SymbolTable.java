import java.util.Hashtable;

import AST.Identifier;

public class SymbolTable {

    private Hashtable<String, Identifier> globalTable = new Hashtable<>();
    private Hashtable<String, Identifier> localTable = new Hashtable<>();

    public SymbolTable() {
        // Adicionar funções pré-definidas
    }

    public void putLocal(Identifier identifier) {
        localTable.put(identifier.getName(), identifier);
    }

    public void clearLocal() {
        localTable.clear();
    }

    public void put(Identifier identifier) {
        globalTable.put(identifier.getName(), identifier);
    }

    public Identifier get(String name) {
        return localTable.containsKey(name) ? localTable.get(name) : globalTable.get(name);
    }

    public Identifier get(Identifier identifier) {
        return get(identifier.getName());
    }

    public boolean has(String name) {
        return localTable.containsKey(name) || globalTable.containsKey(name);
    }

    public boolean has(Identifier identifier) {
        return has(identifier.getName());
    }
}