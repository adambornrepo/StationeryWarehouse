package dataaccess.concretes;

import dataaccess.abstracts.DataOperations;
import dataaccess.abstracts.Gateway;
import entities.concretes.Notebook;

import java.io.*;
import java.util.List;

public class NotebookDataAccess extends Gateway implements DataOperations {
    List<Notebook> notebookList;

    public NotebookDataAccess(List<Notebook> notebookList) {
        this.notebookList = notebookList;
    }

    @Override
    public void read() {
        try {
            File file = new File(path + "NotebookData");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String notebooks = null;
            while ((notebooks = br.readLine()) != null) {
                String[] param = notebooks.split(" ");
                Notebook notebook = new Notebook(Integer.parseInt(param[0]), param[1], param[2], Integer.parseInt(param[3]), Integer.parseInt(param[4]), Integer.parseInt(param[5]));
                notebookList.add(notebook);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void write() {
        try {
            File file = new File(path + "NotebookData");
            FileWriter fr = new FileWriter(file);
            BufferedWriter br = new BufferedWriter(fr);
            for (Notebook notebook : notebookList) {
                br.write(notebook.toString() + "\n");
                br.flush();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
