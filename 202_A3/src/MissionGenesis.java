// Class representing the mission of Genesis


import org.w3c.dom.*;
import javax.xml.parsers.*;

import java.io.File;
import java.util.*;

public class MissionGenesis {

    // Private fields
    private MolecularData molecularDataHuman; // Molecular data for humans
    private MolecularData molecularDataVitales; // Molecular data for Vitales

    // Getter for human molecular data
    public MolecularData getMolecularDataHuman() {
        return molecularDataHuman;
    }

    // Getter for Vitales molecular data
    public MolecularData getMolecularDataVitales() {
        return molecularDataVitales;
    }

    // Method to read XML data from the specified filename
    // This method should populate molecularDataHuman and molecularDataVitales fields once called

    public void readXML(String filename) {

        try {
            File inputFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Parse HumanMolecularData
            NodeList humanList = doc.getElementsByTagName("HumanMolecularData");
            molecularDataHuman = parseMolecularData(humanList);

            // Parse VitalesMolecularData
            NodeList vitalesList = doc.getElementsByTagName("VitalesMolecularData");
            molecularDataVitales = parseMolecularData(vitalesList);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private MolecularData parseMolecularData(NodeList nodeList) {
        // Create a list to hold molecules
        List<Molecule> molecules = new ArrayList<>();

        // Iterate through each molecule node
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element moleculeElement = (Element) nodeList.item(i);
            NodeList moleculeList = moleculeElement.getElementsByTagName("Molecule");

            // Iterate through each molecule
            for (int j = 0; j < moleculeList.getLength(); j++) {
                Element molecule = (Element) moleculeList.item(j);


                // Extract molecule data
                String id = molecule.getElementsByTagName("ID").item(0).getTextContent();
                int bondStrength = Integer
                        .parseInt(molecule.getElementsByTagName("BondStrength").item(0).getTextContent());

                // Extract bonds
                List<String> bonds = new ArrayList<>();
                NodeList bondsList = molecule.getElementsByTagName("MoleculeID");
                for (int k = 0; k < bondsList.getLength(); k++) {
                    Element bond = (Element) bondsList.item(k);
                    bonds.add(bond.getTextContent());
                }

                // Create Molecule object and add to the list
                Molecule newMolecule = new Molecule(id, bondStrength, bonds);
                molecules.add(newMolecule);
            }
        }

        // Create and return MolecularData object
        return new MolecularData(molecules);
    }
}
