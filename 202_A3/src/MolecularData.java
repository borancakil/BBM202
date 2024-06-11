import java.util.*;

// Class representing molecular data
public class MolecularData {

    // Private fields
    private final List<Molecule> molecules; // List of molecules

    // Constructor
    public MolecularData(List<Molecule> molecules) {
        this.molecules = molecules;
    }

    // Getter for molecules
    public List<Molecule> getMolecules() {
        return molecules;
    }

    // Method to identify molecular structures
    // Return the list of different molecular structures identified from the input data
    public List<MolecularStructure> identifyMolecularStructures() {
        ArrayList<MolecularStructure> structures = new ArrayList<>();

        Set<Molecule> visited = new HashSet<>();

        for (Molecule molecule : molecules) {
            if (!visited.contains(molecule)) {
                MolecularStructure structure = new MolecularStructure();
                dfs(molecule, structure, visited);
                structures.add(structure);
            }
        }

        return structures;
    }
    
    private void dfs(Molecule molecule, MolecularStructure structure, Set<Molecule> visited) {
        visited.add(molecule);
        structure.addMolecule(molecule);
        for (String connectedId : molecule.getBonds()) {
            Molecule connectedMolecule = findMoleculeById(connectedId);
            if (connectedMolecule != null && !visited.contains(connectedMolecule)) {
                dfs(connectedMolecule, structure, visited);
            }
        }
    }

    private Molecule findMoleculeById(String id) {
        for (Molecule molecule : molecules) {
            if (molecule.getId().equals(id)) {
                return molecule;
            }
        }
        return null;
    }



    // Method to print given molecular structures
    @SuppressWarnings("unchecked")
    public void printMolecularStructures(List<MolecularStructure> molecularStructures, String species) {
        System.out
                .println(molecularStructures.size() + " molecular structures have been discovered in " + species + ".");
        for (int i = 0; i < molecularStructures.size(); i++) {
            MolecularStructure structure = molecularStructures.get(i);
            List<Molecule> moleculesInStructure = structure.getMolecules();
            Collections.sort(moleculesInStructure); // Sort molecules by ID
            System.out.println("Molecules in Molecular Structure " + (i + 1) + ": " + moleculesInStructure);
        }
    }


    // Method to identify anomalies given a source and target molecular structure
    // Returns a list of molecular structures unique to the targetStructure only
    public static ArrayList<MolecularStructure> getVitalesAnomaly(List<MolecularStructure> sourceStructures,
            List<MolecularStructure> targetStructures) {
        ArrayList<MolecularStructure> anomalyList = new ArrayList<>();
        for (MolecularStructure targetStructure : targetStructures) {
            boolean isUnique = true;
            for (MolecularStructure sourceStructure : sourceStructures) {
                if (targetStructure.equals(sourceStructure)) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                anomalyList.add(targetStructure);
            }
        }
        return anomalyList;
    }


    // Method to print Vitales anomalies
    public void printVitalesAnomaly(List<MolecularStructure> molecularStructures) {
        System.out.println("Molecular structures unique to Vitales individuals:");
        for (MolecularStructure structure : molecularStructures) {
            System.out.println(structure.getMolecules());
        }
    }

}
