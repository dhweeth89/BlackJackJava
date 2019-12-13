/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohnish
 */

import java.net.UnknownHostException;
import static com.mongodb.client.model.Projections.excludeId;
import com.mongodb.QueryBuilder;
import com.mongodb.BasicDBObject;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Graphics;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.DB;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;
import com.mongodb.DBCursor;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.Node;

public class EverytingMongo 
{
 public static void main(String[] args) throws NoSuchAlgorithmException
   {
      Signup first = new Signup();
   }
}

class Signup
{
    private int money;
    
    public Signup() throws NoSuchAlgorithmException
    {
        money = 1000;
    }          
    
    public boolean MakeAccount(String user, String pass) throws NoSuchAlgorithmException
    {
        if (this.Authentication(user))
        {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("MongoTester");
        MongoCollection<Document> collection = database.getCollection("accounts");
        
        
        
        Document username = new Document("username", Authentication(user))
                .append("password", toHexString(getSHA(pass)))
                .append("money", money);
        collection.insertOne(username);
        
        System.out.println("Your account has been successfully created!");
        return true;
        }
        else
        {
            return false;
        }
            
    }
    
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {  
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
  
        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    
    public static String toHexString(byte[] hash) 
    { 
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);  
  
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros 
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
  
        return hexString.toString();  
    }
    
    public static boolean Authentication(String input)
    {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("MongoTester");
        MongoCollection<Document> collection = database.getCollection("accounts");
                   
            Document document = collection
            .find(new BasicDBObject("username", input))
                .projection(Projections.fields(Projections.include("username"), Projections.excludeId())).first();
        
            
            if (document != null)
            {
                return false;
            }
            else
            {
                return true;
            }
                /*
                x = document.getString("username");
                System.out.println(x.getClass());
            
                if (x.equals(input))
                {
                    Scanner newuser = new Scanner(System.in); 
                    System.out.println("This username is taken");
                    System.out.println("Please enter another username:");
                    input = newuser.nextLine();
                    x = input;
                    
                    Scanner newpassword = new Scanner(System.in);
                    System.out.println("Enter a new password");
                    */   
    }
    
    public static String getStringFromDocument(Document doc)
    {
    try
    {
       DOMSource domSource = new DOMSource((Node) doc);
       StringWriter writer = new StringWriter();
       StreamResult result = new StreamResult(writer);
       TransformerFactory tf = TransformerFactory.newInstance();
       Transformer transformer = tf.newTransformer();
       transformer.transform(domSource, result);
       return writer.toString();
    }
    catch(TransformerException ex)
    {
       ex.printStackTrace();
       return null;
    }
    }
    
}
/*
class Login
{
    public Login()
    {
    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    MongoDatabase database = mongoClient.getDatabase("blackjack");
    MongoCollection<Document> collection = database.getCollection("accounts");    
  
    Scanner input = new Scanner(System.in);
    System.out.println("Please enter your username:");
    String username  = input.nextLine();

    Scanner pw = new Scanner(System.in);
    System.out.println("Please enter your password:");
    String password = pw.nextLine();
    
    if (AuthenticateUsername(username) == true && AuthenticatePassword(username, password) == true)
    {
        System.out.println("You have successfully logged in!");
    }
    
    else
    {
        System.out.println("One or more of the fields you have entered are incorrect.");
    }
    
    }
    
    public static boolean AuthenticateUsername(String input)
    {
    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    MongoDatabase database = mongoClient.getDatabase("blackjack");
    MongoCollection<Document> collection = database.getCollection("accounts");
    
    
    Document myDoc = collection.find(eq("username", input)).first();
    String x = getStringFromDocument(myDoc);
    
    if (x == input)
    {
        return true;
    }
    
    else 
    {
        return false;
    }
    }
    
    public static boolean AuthenticatePassword(String username, String password)
    {
    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    MongoDatabase database = mongoClient.getDatabase("blackjack");
    MongoCollection<Document> collection = database.getCollection("accounts");
    
    Document myDoc = (Document) collection.find().projection(fields(include("username", username), excludeId()));
    String x = getStringFromDocument(myDoc);
    
    
    
    Document myDoc2 = (Document) collection.find().projection(fields(include("password", password), excludeId()));
    String y = getStringFromDocument(myDoc2);
    
    
    
    
    
  //  BasicDBObject criteria = new BasicDBObject();
   // criteria.append("username", username);
   // criteria.append("password", password);
    
   // DBCursor cur = collection.find(criteria);
   // Document myDoc = collection.find(eq(criteria));
   // String x = getStringFromDocument(myDoc); 
    
    
    
    
    }
    
    public static String getStringFromDocument(Document doc)
    {
    try
    {
       DOMSource domSource = new DOMSource((Node) doc);
       StringWriter writer = new StringWriter();
       StreamResult result = new StreamResult(writer);
       TransformerFactory tf = TransformerFactory.newInstance();
       Transformer transformer = tf.newTransformer();
       transformer.transform(domSource, result);
       return writer.toString();
    }
    catch(TransformerException ex)
    {
       ex.printStackTrace();
       return null;
    }
    }
    
}
*/