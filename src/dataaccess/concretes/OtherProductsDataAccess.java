package dataaccess.concretes;

import dataaccess.abstracts.DataOperations;
import dataaccess.abstracts.Gateway;
import entities.concretes.OtherProduct;
import java.io.*;
import java.util.List;

public class OtherProductsDataAccess extends Gateway implements DataOperations {
    List<OtherProduct> otherProductsList;

    public OtherProductsDataAccess(List<OtherProduct> otherProductsList) {
        this.otherProductsList = otherProductsList;
    }

    @Override
    public void read() {
        try {
            File file = new File(path + "\\OtherProductData");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String products = null;
            while ((products = br.readLine()) != null) {
                String[] param = products.split(" ");
                OtherProduct op = new OtherProduct(Integer.parseInt(param[0]), param[1],Integer.parseInt(param[2]), Integer.parseInt(param[3]), param[4]);
                otherProductsList.add(op);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void write() {
        try {
            File file = new File(path + "\\OtherProductData");
            FileWriter fr = new FileWriter(file);
            BufferedWriter br = new BufferedWriter(fr);
            for (OtherProduct product : otherProductsList) {
                br.write(product.toString() + "\n");
                br.flush();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
