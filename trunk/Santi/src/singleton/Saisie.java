package singleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe sur le pattern singleton, on crée un seul objet IN que l'on appelle ( Saisie.IN.méthode() ) à chaque fois pour
 * demander des saisies à l'utilisateur.
 * Ces demandes de saisies sont vérifiée et les erreurs sont toutes géré dans la classe
 */
public class Saisie {
    public final static Saisie IN = new Saisie();
    
    private BufferedReader buffer;
    
    private Saisie(){
        this.buffer = new BufferedReader (new InputStreamReader (System.in)); 
    }
    
    /**
     * Méthode qui demande à l'utilisateur de saisir un entier
     * Elle n'accepte pas que l'utilisateur ne tape rien
     * Ni que celui-ci saisissent un entier négatif
     * @return un entier supérieur ou égal à zéro 
     */
    public int nextIntNotBlank(){
        boolean correct = false;
        int valeur = 0;
        String line;
        while(!correct){
            try {
                line = this.buffer.readLine();
                while (line.isEmpty()){
                    System.out.println("Allez.. Un petit effort, écrivez quelque chose !");
                    line = this.buffer.readLine();   
                }
                valeur = Integer.valueOf(line);
                if (valeur < 0){
                    throw new NumberFormatException();
                }
                // Si on ne lance pas l'exception on va forcément renvoyer true, tout s'est bien déroulé
                correct = true;
            } catch (NumberFormatException numE){
                correct = false;
                valeur = 0;
                System.out.println("Mauvaise saisie, retentez votre chance");
            } catch (IOException ex) {
                Logger.getLogger(Saisie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return valeur;
    }
    
    /**
     * Méthode qui demande à l'utilisateur de saisir un entier
     * Elle n'accepte pas que l'utilisateur ne tape rien
     * Ni que celui-ci saisissent un entier négatif
     * @param notBlank message qui est écrit quand l'utilisateur n'écrit rien
     * @param exception les message écrit quand une exception de type numberFormat est levé
     * @param message préçision sur ce que l'on demande comme saisie à l'utilisateur
     * @return un entier supérieur ou égal à zéro 
     */
    public int nextIntNotBlank(String notBlank, String exception, String message){
        boolean correct = false;
        int valeur = 0;
        String line;
        while(!correct){
            try {
                System.out.println(message);
                line = this.buffer.readLine();
                while (line.isEmpty()){
                    System.out.println(notBlank);
                    line = this.buffer.readLine();   
                }
                valeur = Integer.valueOf(line);
                if (valeur < 0){
                    throw new NumberFormatException();
                }
                // Si on ne lance pas l'exception on va forcément renvoyer true, tout s'est bien déroulé
                correct = true;
            } catch (NumberFormatException numE){
                correct = false;
                valeur = 0;
                System.out.println(exception);
            } catch (IOException ex) {
                Logger.getLogger(Saisie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return valeur;
    }
    
    /**
     * Méthode qui demande à l'utilisateur de saisir un entier
     * On tolère que l'utilisateur ne tape aucune valeur
     * On ne tolèr pas que celui-ci saisissent un entier négatif
     * 
     * @return -1 Si l'utilisateur n'entre rien et sinon entier supérieur ou égal à 0
     */
    public int nextIntBlank(){
        boolean correct = false;
        int valeur = -1;
        String line;
        while (!correct){
            try {
                line = this.buffer.readLine();
                if(line.isEmpty()){
                    valeur = -1;
                    correct = true;
                }else{
                    valeur = Integer.valueOf(line);
                    if (valeur < 0){
                        throw new NumberFormatException();
                    }
                    // Si on ne lance pas l'exception on va forcément renvoyer true, tout s'est bien déroulé
                    correct = true;
                }    
            } catch (NumberFormatException numE){
                correct = false;
                System.out.println("Mauvaise saisie, retentez votre chance");
            } catch (IOException ex) {
                Logger.getLogger(Saisie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return valeur;
    }
    
    /**
     * Méthode qui demande à l'utilisateur de saisir un entier
     * On tolère que l'utilisateur ne tape aucune valeur
     * On ne tolèr pas que celui-ci saisissent un entier négatif
     * @param exception les message écrit quand une exception de type numberFormat est levé
     * @param message préçision sur ce que l'on demande comme saisie à l'utilisateur
     * @return -1 Si l'utilisateur n'entre rien et sinon entier supérieur ou égal à 0
     */
    public int nextIntBlank(String exception, String message){
        boolean correct = false;
        int valeur = -1;
        String line;
        while (!correct){
            try {
                System.out.println(message);
                line = this.buffer.readLine();
                if(line.isEmpty()){
                    valeur = -1;
                    correct = true;
                }else{
                    valeur = Integer.valueOf(line);
                    if (valeur < 0){
                        throw new NumberFormatException();
                    }
                    // Si on ne lance pas l'exception on va forcément renvoyer true, tout s'est bien déroulé
                    correct = true;
                }    
            } catch (NumberFormatException numE){
                correct = false;
                System.out.println(exception);
            } catch (IOException ex) {
                Logger.getLogger(Saisie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return valeur;
    }
    
    /**
     * Méthode qui demande à l'utilisateur de saisir un entier compris en min et max
     * Ne tolère pas que l'utilisateur ne tape rien
     * 
     * @param min la valeur minimum que l'utilisateur peut entrer
     * @param max la valeur max que l'utilisateur peut entrer
     * @return le valeur qu'à saisie l'utilisateur entre min et max
     */
    public int nextIntWithRangeNotBlank(int min, int max){
        int valeur = -1;
        boolean correct = false;
        String line;
        while(!correct){
            try {
                line = this.buffer.readLine();
                if (!line.isEmpty()){
                    valeur = Integer.valueOf(line);
                    if ((valeur >= min)&&(valeur <= max)){
                        correct = true;
                    }else{
                        correct = false;
                        System.out.println("La valeur doit-être comprise entre "+min+" et "+max);
                    }
                }else{
                    valeur = -1;
                    correct = false;
                    System.out.println("Allez.. Un petit effort, écrivez quelque chose !");
                }
            } catch (NumberFormatException numE){
                    correct = false;
                    System.out.println("Mauvaise saisie, retentez votre chance");    
            } catch (IOException ex) {
                Logger.getLogger(Saisie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return valeur;
    }
    
    /**
     * Méthode qui demande à l'utilisateur de saisir un entier compris en min et max
     * Ne tolère pas que l'utilisateur ne tape rien
     * 
     * @param min la valeur minimum que l'utilisateur peut entrer
     * @param max la valeur max que l'utilisateur peut entrer
     * @param notBlank message qui est écrit quand l'utilisateur n'écrit rien
     * @param exception les message écrit quand une exception de type numberFormat est levé
     * @param message préçision sur ce que l'on demande comme saisie à l'utilisateur
     * 
     * @return le valeur qu'à saisie l'utilisateur entre min et max
     */
    public int nextIntWithRangeNotBlank(int min, int max,String notBlank, String exception, String message){
        int valeur = -1;
        boolean correct = false;
        String line;
        while(!correct){
            try {
                System.out.println(message);
                line = this.buffer.readLine();
                if (!line.isEmpty()){
                    valeur = Integer.valueOf(line);
                    if ((valeur >= min)&&(valeur <= max)){
                        correct = true;
                    }else{
                        correct = false;
                        System.out.println("La valeur doit-être comprise entre "+min+" et "+max);
                    }
                }else{
                    valeur = -1;
                    correct = false;
                    System.out.println(notBlank);
                }
            } catch (NumberFormatException numE){
                    correct = false;
                    System.out.println(exception);    
            } catch (IOException ex) {
                Logger.getLogger(Saisie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return valeur;
    }
    
    /**
     * Méthode qui demande à l'utilisateur de saisir un entier compris en min et max
     * Elle tolère que l'utilisateur ne tape rien
     * 
     * @param min la valeur minimum que l'utilisateur peut entrer
     * @param max la valeur max que l'utilisateur peut entrer
     * @return -1 si l'utilisateur ne saisie rien sinon la valeur qu'il a tapé compris entre min et max
     */
    public int nextIntWithRange(int min, int max){
        int valeur = -1;
        boolean correct = false;
        String line;
        while(!correct){
            try {
                line = this.buffer.readLine();
                if (!line.isEmpty()){
                    valeur = Integer.valueOf(line);
                    if ((valeur >= min)&&(valeur <= max)){
                        correct = true;
                    }else{
                        correct = false;
                        System.out.println("La valeur doit-être comprise entre "+min+" et "+max);
                    }
                }else{
                    valeur = -1;
                    correct = true;
                    System.out.println("Vous avez laissé le même nombre de sucre");
                }
            } catch (NumberFormatException numE){
                    correct = false;
                    System.out.println("Mauvaise saisie, retentez votre chance");    
            } catch (IOException ex) {
                Logger.getLogger(Saisie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return valeur;
    }
    
    /**
     * Méthode qui demande à l'utilisateur de saisir un entier compris en min et max
     * Elle tolère que l'utilisateur ne tape rien
     * 
     * @param min la valeur minimum que l'utilisateur peut entrer
     * @param max la valeur max que l'utilisateur peut entrer
     * @param exception les message écrit quand une exception de type numberFormat est levé
     * @param message préçision sur ce que l'on demande comme saisie à l'utilisateur
     * @return -1 si l'utilisateur ne saisie rien sinon la valeur qu'il a tapé compris entre min et max
     */
    public int nextIntWithRange(int min, int max, String exception, String message){
        int valeur = -1;
        boolean correct = false;
        String line;
        while(!correct){
            try {
                System.out.println(message);
                line = this.buffer.readLine();
                if (!line.isEmpty()){
                    valeur = Integer.valueOf(line);
                    if ((valeur >= min)&&(valeur <= max)){
                        correct = true;
                    }else{
                        correct = false;
                        System.out.println("La valeur doit-être comprise entre "+min+" et "+max);
                    }
                }else{
                    valeur = -1;
                    correct = true;
                }
            } catch (NumberFormatException numE){
                    correct = false;
                    System.out.println(exception);    
            } catch (IOException ex) {
                Logger.getLogger(Saisie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return valeur;
    }
    
    /**
     * Demande à l'utilisateur une saisie alphanumérique
     * Ne tolère pas que l'utilisateur ne tape rien
     * 
     * @return la ligne qu'à saisie l'utilisateur
     */
    public String nextLineNotBlank(){
        String line = "";
        try {
            line = this.buffer.readLine ();
            while (line.isEmpty()){
                System.out.println("Allez.. Un petit effort, écrivez quelque chose !");
                line = this.buffer.readLine();   
            }
        } catch (IOException ex) {
            Logger.getLogger(Saisie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return line;
    }
    
    /**
     * Demande à l'utilisateur une saisie alphanumérique
     * Ne tolère pas que l'utilisateur ne tape rien
     * 
     * @return la ligne qu'à saisie l'utilisateur
     */
    public String nextLineNotBlank(String notBlank, String exception, String message){
        String line = "";
        try {
            System.out.println(message);
            line = this.buffer.readLine ();
            while (line.isEmpty()){
                System.out.println(notBlank);
                line = this.buffer.readLine();   
            }
        } catch (IOException ex) {
            System.out.println(exception);
            Logger.getLogger(Saisie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return line;
    }
}
