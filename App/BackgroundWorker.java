package com.example.leandri.itrw_324;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Leandri on 2017-09-05.
 */

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    Context context;

    private ArrayList<String> titlesArrayList = new ArrayList<>();
    private ArrayList<String> editionsArrayList = new ArrayList<>();
    private ArrayList<String> pricesArrayList = new ArrayList<>();
    private ArrayList<String> authorsArrayList = new ArrayList<>();
    private ArrayList<String> textbookIDsArrayList = new ArrayList<>();

    private String firstName;
    private String lastName;
    private String username;
    private String contactNum;
    private String email;

    private String textbookTitle;
    private String textbookAuthor;
    private String textbookPrice;
    private String textbookComments;
    private String sellerUsername;
    private String sellerUserID;

    private static String userID;
    private boolean isDone;
    private boolean noResults;

    BackgroundWorker(Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String searchContent = params[1];
        username = params[2];
        String password = params[3];
        firstName = params[4];
        lastName = params[5];
        contactNum = params[6];
        email = params[7];
        String textbookID = params[8];

        String search_url = "http://10.0.2.2/SearchTextbook.php";
        String main_url = "http://10.0.2.2/SelectTextbooks.php";
        String login_url = "http://10.0.2.2/LoginValidation.php";
        String register_url = "http://10.0.2.2/RegisterUser.php";
        String profile_url = "http://10.0.2.2/RetrieveProfile.php";
        String profileTextbooks_url = "http://10.0.2.2/RetrieveUserTextbooks.php";
        String textbook_url = "http://10.0.2.2/RetrieveTextbookDetails.php";
        String result = "";

        if(type.equals("search")){
            try {
                isDone = false;
                noResults = false;

                titlesArrayList.clear();
                editionsArrayList.clear();
                authorsArrayList.clear();
                pricesArrayList.clear();
                textbookIDsArrayList.clear();

                URL url = new URL(search_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("search_content", "UTF-8") + "=" + URLEncoder.encode(searchContent, "UTF-8");

                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;
                String[] lineItems;

                while ((line = bufferedReader.readLine()) != null){
                    if(line.equals("0 results")){
                        noResults = true;
                    }
                    else {
                        lineItems = line.split(",");

                        titlesArrayList.add(lineItems[0]);
                        editionsArrayList.add(lineItems[1]);
                        pricesArrayList.add(lineItems[2]);
                        authorsArrayList.add(lineItems[3]);
                        textbookIDsArrayList.add(lineItems[4]);
                    }
                }

                isDone = true;

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(type.equals("main")){
            try {
                isDone = false;

                titlesArrayList.clear();
                editionsArrayList.clear();
                authorsArrayList.clear();
                pricesArrayList.clear();
                textbookIDsArrayList.clear();

                URL url = new URL(main_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;
                String[] lineItems;

                while ((line = bufferedReader.readLine()) != null){
                    lineItems = line.split(",");

                    titlesArrayList.add(lineItems[0]);
                    editionsArrayList.add(lineItems[1]);
                    pricesArrayList.add(lineItems[2]);
                    authorsArrayList.add(lineItems[3]);
                    textbookIDsArrayList.add(lineItems[4]);
                }

                isDone = true;

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(type.equals("login")){
            try {
                isDone = false;
                noResults = false;

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    if(line.equals("Incorrect credentials"))
                        noResults = true;
                    else
                        setUserID(line);
                }

                isDone = true;

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(type.equals("register")){
            try {
                isDone = false;
                noResults = false;

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("firstName", "UTF-8") + "=" + URLEncoder.encode(firstName, "UTF-8") + "&" +
                        URLEncoder.encode("lastName", "UTF-8") + "=" + URLEncoder.encode(lastName, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                        URLEncoder.encode("contactNum", "UTF-8") + "=" + URLEncoder.encode(contactNum, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    if(line.equals("Unsuccessful"))
                        noResults = true;
                    else
                        setUserID(line);
                }

                isDone = true;

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(type.equals("profile")){
            try {
                sellerUserID = params[9];
                isDone = false;
                noResults = false;

                titlesArrayList.clear();
                editionsArrayList.clear();
                authorsArrayList.clear();
                pricesArrayList.clear();
                textbookIDsArrayList.clear();

                URL url = new URL(profile_url);
                URL url2 = new URL(profileTextbooks_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) url2.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection2.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection2.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection2.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStream outputStream2 = httpURLConnection2.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStream2, "UTF-8"));
                String postData = "";
                if(sellerUserID.equals("")){
                    postData = URLEncoder.encode("userID", "UTF-8") + "=" + URLEncoder.encode(("" + getUserID()), "UTF-8");
                }
                else{
                    postData = URLEncoder.encode("userID", "UTF-8") + "=" + URLEncoder.encode(("" + sellerUserID), "UTF-8");
                }

                bufferedWriter.write(postData);
                bufferedWriter2.write(postData);
                bufferedWriter.flush();
                bufferedWriter2.flush();
                bufferedWriter.close();
                bufferedWriter2.close();
                outputStream.close();
                outputStream2.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStream inputStream2 = httpURLConnection2.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2, "iso-8859-1"));
                String line;
                String line2;
                String[] lineItems;
                String[] lineItems2;

                while ((line = bufferedReader.readLine()) != null){
                    lineItems = line.split(",");

                    firstName = lineItems[0];
                    lastName = lineItems[1];
                    username = lineItems[2];
                    contactNum = lineItems[3];
                    email = lineItems[4];
                }

                while ((line2 = bufferedReader2.readLine()) != null){
                    if(line2.equals("0 results")){
                        noResults = true;
                    }
                    else{
                        lineItems2 = line2.split(",");

                        titlesArrayList.add(lineItems2[0]);
                        editionsArrayList.add(lineItems2[1]);
                        pricesArrayList.add(lineItems2[2]);
                        authorsArrayList.add(lineItems2[3]);
                        textbookIDsArrayList.add(lineItems2[4]);
                    }
                }

                isDone = true;

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(type.equals("textbook")){
            try {
                isDone = false;
                noResults = false;

                URL url = new URL(textbook_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("textbookID", "UTF-8") + "=" + URLEncoder.encode(textbookID, "UTF-8");

                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line;
                String[] lineItems;

                while ((line = bufferedReader.readLine()) != null){
                    if(line.equals("0 results")){
                        noResults = true;
                    }
                    else {
                        lineItems = line.split(";");

                        textbookTitle = lineItems[0] + ", Edition " + lineItems[1];
                        textbookPrice = lineItems[2];
                        textbookAuthor = lineItems[3];
                        textbookComments = lineItems[4];
                        sellerUsername = lineItems[5];
                        sellerUserID = lineItems[6];
                    }
                }

                isDone = true;

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }



    @Override
    protected void onPreExecute() {


    }

    @Override
    protected void onPostExecute(String result) {

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public ArrayList<String> getTitlesArrayList()
    {
        return titlesArrayList;
    }

    public ArrayList<String> getEditionsArrayList()
    {
        return editionsArrayList;
    }

    public ArrayList<String> getPriceArrayList()
    {
        return pricesArrayList;
    }

    public ArrayList<String> getAuthorsArrayList()
    {
        return authorsArrayList;
    }

    public ArrayList<String> getTextbookIDsArrayList()
    {
        return textbookIDsArrayList;
    }

    public boolean getIsDone(){ return isDone;}

    public boolean getNoResults(){ return noResults;}

    public void setUserID(String loggedInUserID){
        userID = loggedInUserID;
    }

    public String getUserID(){ return userID;}

    public String getFirstName(){ return  firstName;}

    public String getLastName() {return  lastName;}

    public String getUsername(){return username;}

    public String getContactNum(){return contactNum;}

    public String getEmail(){return email;}

    public String getTextbookTitle(){ return  textbookTitle;}

    public String getTextbookPrice(){ return textbookPrice;}

    public String getTextbookAuthor(){ return textbookAuthor;}

    public String getTextbookComments(){ return textbookComments;}

    public String getSellerUsername(){ return sellerUsername;}

    public String getSellerUserID(){ return  sellerUserID;}
}
