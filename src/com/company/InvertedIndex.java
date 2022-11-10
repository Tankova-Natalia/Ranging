package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;




public class InvertedIndex {
    List<String> documents = new ArrayList<String>();
    Map<String, Term> index = new HashMap<>();
    int countTokens = 0;

    public void indexDocument(String path) throws IOException {
        if (!documents.contains(path)) {
            Integer docId = documents.size();
            documents.add(docId, path);
            //Document doc = (Document) Jsoup.parse(file, "UTF-8");
            File input = new File(path);
            Document doc = (Document) Jsoup.parse(input, "UTF-8");
            String content = doc.body().text().toLowerCase();
            String[] words = content.split("[^a-zA-Z0-9_']+");
            boolean isExist = false;
            for (int i = 0; i < words.length; i++) {
                Term idx = index.get(words[i]);
                isExist = false;
                Stemmer stemmer = new Stemmer();
                stemmer.add(words[i].toCharArray(), words[i].length());
                stemmer.stem();
                String term = stemmer.toString();
                if(idx == null){
                    idx = new Term(docId);
                        index.put(term, idx);
                        isExist = true;

                }
                if (isExist == true || idx.list.getLast().getDocID() != docId){
                    idx.addDocument(docId);
                }
            }
            countTokens += index.size();
            System.out.printf("| %2d | %60s | %5d |%n", docId, path,
                    index.size());
        }
    }

    public void indexCollection(String folder) throws IOException {
        File dir = new File(folder);
        String[] files = dir.list();
        for (int i = 0; i < files.length; i++) {
            this.indexDocument(folder + "\\" + files[i]);
        }
    }

    public List<Integer> getAllDoc(){
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < documents.size(); i++){
            list.add(i);
        }
        return list;
    }
    public List<Integer> executeQuery(String query) {
        return null;
    }

    public void printDocuments(List query){
        Integer docId;
        if(query!=null){
            for(Iterator i = query.iterator();i.hasNext();){
                docId = (Integer)i.next();
                String s = this.documents.get(docId);
                System.out.println(s.substring(s.indexOf('\\') + 1,
                        s.indexOf('.')));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        InvertedIndex myIndex = new InvertedIndex();
        myIndex.indexCollection("collection_html");
        System.out.println("Count tokens " + myIndex.countTokens);
        System.out.println("Size " + myIndex.index.size());
        String query1 = "goal";
        String query2 = "sun";
        String query3 = "king";
        String query4 = "about";
        String query5 = "romeo and juliet";
        String query6 = "goal and sun and about";
        String query7 = "romeo and juliet and king";
        String query8 = "fled and thrown and leaden and twain";
        System.out.println("");
        /*System.out.println(query1);
        myIndex.printDocuments(myIndex.executeQuery(query1));
        System.out.println("");
        System.out.println(query2);
        myIndex.printDocuments(myIndex.executeQuery(query2));
        System.out.println("");
        System.out.println(query3);
        myIndex.printDocuments(myIndex.executeQuery(query3));
        System.out.println("");
        System.out.println(query4);
        myIndex.printDocuments(myIndex.executeQuery(query4));
        System.out.println("");
        System.out.println(query5);
        myIndex.printDocuments(myIndex.executeQuery(query5));
        System.out.println("");
        System.out.println(query6);
        myIndex.printDocuments(myIndex.executeQuery(query6));
        System.out.println("");
        System.out.println(query7);
        myIndex.printDocuments(myIndex.executeQuery(query7));
        System.out.println("");
        System.out.println(query8);
        myIndex.printDocuments(myIndex.executeQuery(query8));

         */
        TermDocument term = new TermDocument(0);
        term.computeTfIdf(2);
        System.out.println(term);
    }
}
