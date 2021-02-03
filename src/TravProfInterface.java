import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.Set;

public class TravProfInterface {

    //All private fields are used within the interface but cannot be accessed outside. Here they are declared.
    private static String ID;
    private String filename;
    private TravProfDB database;
    private File newFile;

    //TAKES IN STRING FILENAME AND INITIALIZES IT ALONG WITH THE TRAVPROFDB AND FILE
    public TravProfInterface(String filename) {
        this.filename = filename;
        database = new TravProfDB(filename);
        newFile = new File(filename);
    }

    //USER CHOICE IS INPUTTED VIA SCANNER. CORRESPONDING SWITCH CASE DOES THE REQUIRED ACTION.
    public void getUserChoice() throws IOException, ClassNotFoundException {
        boolean input = true;
        //SELECTION PROMPTS ARE CALLED UNTIL VALID CHOICE IS GIVEN BY USER.
        while (input)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please choose the number corresponding to your selection: ");
            System.out.println("1. Create a New Profile");
            System.out.println("2. Edit Existing Profile");
            System.out.println("3. Delete a Profile");
            System.out.println("4. Search Profiles");
            System.out.println("5. Display All Profiles");
            System.out.println("6. Write to Database");
            System.out.println("7. Initialize Database");
            System.out.println("8. Exit ITS");

            int choice = 0;
            //HANDLES THE CASE OF NEXTINT() NOT RECEIVING AN INTEGER INPUT
            try { choice = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid Menu Option. Please try again.\n");
                    break;
                }
            //SWITCH CASE TO DO THE ACTION REQUESTED BY USER
            switch (choice) {
                case 1:
                    System.out.println("\n");
                    TravProf temp = createNewTravProf();
                    if (temp!= null)
                    {
                        displayTravProf(temp);
                    }
                    input = false;
                    break;
                case 2:
                    updateTravProf();
                    System.out.println("\n");
                    input = false;
                    break;
                case 3:
                    deleteTravProf();
                    System.out.println("\n");
                    input = false;
                    break;
                case 4:
                    findTravProf();
                    System.out.println("\n");
                    input = false;
                    break;
                case 5:
                    System.out.println("\n");
                    displayAllTravProf();
                    System.out.println("\n");
                    input = false;
                    break;
                case 6:
                    writeToDB();
                    System.out.println("\n");
                    input = false;
                    break;
                case 7:
                    initDB();
                    System.out.println("\n");
                    input = false;
                    break;
                case 8:
                    System.out.println("Goodbye.");
                    input = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Menu Option. Please try again.\n");
            }
        }

    }

    //TODO: WHAT COUNTS AS A DUPLICATE PROFILE? SAME EVERYTHING OR JUST ID/LASTNAME?
    //EMPTY DATABASE WITH CHECK DUPLICATE
    //CREATES NEW TRAVPROF WITH PROMPTS TO USER.
    public TravProf createNewTravProf() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a first name: ");
        String firstName = sc.nextLine();
        System.out.println("Please enter a last name: ");
        String lastName = sc.nextLine();
        //creates a travprof with just the ID, first name and last name
        TravProf profile = new TravProf(ID, firstName, lastName, null, null, 0, null, null, null);
        //CHECKS IF A PROFILE WITH THE SAME ID AND LAST NAME IS ALREADY IN THE DATABASE
        //IF YES, THEN THIS NEW ONE IS A DUPLICATE PROFILE
        if (checkDuplicate(profile))
        {
            return null;
        }
        System.out.println("Please enter an address: ");
        String address = sc.nextLine();
        System.out.println("Please enter a phone number: ");
        String phone = sc.nextLine();
        System.out.println("Please enter your trip cost in USD: ");
        float tripCost = sc.nextFloat();
        boolean cond = true;
        String travelType = null;
        //CHECKS IF CORRECT INPUTS ARE MADE FOR SWITCH CASES. IF NOT, PROMPTS UNTIL CORRECT INPUT IS GIVEN
        while (cond)
        {
            System.out.println("Please indicate if your travel was (1) for Business or (2) for Pleasure: ");
            int travel = sc.nextInt();
            switch(travel) {
                case 1:
                    travelType = "Business";
                    cond = false;
                    break;
                case 2:
                    travelType = "Pleasure";
                    cond = false;
                    break;
                default:
                    System.out.println("You did not enter a valid choice.");
                    System.out.println("Please try again.");

            }
        }
        String paymentType = null;
        //CHECKS IF CORRECT INPUTS ARE MADE FOR SWITCH CASES. IF NOT, PROMPTS UNTIL CORRECT INPUT IS GIVEN
        while (!cond)
        {
            System.out.println("Please enter your payment type: (1) Credit (2) Check (3) Debit (4) Invoice: ");
            int payment = sc.nextInt();
            switch(payment) {
                case 1:
                    paymentType = "Credit";
                    cond = true;
                    break;
                case 2:
                    paymentType = "Check";
                    cond = true;
                    break;
                case 3:
                    paymentType = "Debit";
                    cond = true;
                    break;
                case 4:
                    paymentType = "Invoice";
                    cond = true;
                    break;
                default:
                    System.out.println("You did not enter a valid choice. Please try again.");
            }
        }

        MedCond md = createNewMedCond();

        profile = new TravProf(ID, firstName, lastName, address, phone, tripCost, travelType, paymentType, md);
        database.insertNewProfile(profile);
        return profile;
    }

    //CREATES NEW MEDCOND WITH PROMPTS TO USER.
    public MedCond createNewMedCond() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your medical contact: ");
        String mdContact = sc.nextLine();
        System.out.println("Please enter your medical phone number: ");
        String mdPhone = sc.nextLine();
        boolean state = true;
        String algType = null;
        //CHECKS IF CORRECT INPUTS ARE MADE FOR SWITCH CASES. IF NOT, PROMPTS UNTIL CORRECT INPUT IS GIVEN
        while (state){
            System.out.println("Please enter your allergy type: (1) None, (2) Food,(3) Medication, (4) Other");
            int alg = sc.nextInt();
            switch(alg) {
                case 1:
                    algType = "None";
                    state = false;
                    break;
                case 2:
                    algType = "Food";
                    state = false;
                    break;
                case 3:
                    algType = "Medication";
                    state = false;
                    break;
                case 4:
                    algType = "Other";
                    state = false;
                    break;
                default:
                    System.out.println("You did not enter a valid choice. Please try again.");
            }

        }

        String illType = null;
        //CHECKS IF CORRECT INPUTS ARE MADE FOR SWITCH CASES. IF NOT, PROMPTS UNTIL CORRECT INPUT IS GIVEN
        //THE
        while (!state)
        {
            System.out.println("Please enter your medical condition: (1) None, (2) Heart, (3) Diabetes, (4) Asthma, (5) Other");
            int ill = sc.nextInt();
            switch (ill) {
                case 1:
                    illType = "None";
                    state = true;
                    break;
                case 2:
                    illType = "Heart";
                    state = true;
                    break;
                case 3:
                    illType = "Diabetes";
                    state = true;
                    break;
                case 4:
                    illType = "Asthma";
                    state = true;
                    break;
                case 5:
                    illType = "Other";
                    state = true;
                    break;
                default:
                    System.out.println("You did not enter a valid choice. Please try again.");
            }
        }

        return new MedCond(mdContact, mdPhone, algType, illType);
    }

    //CHECKS IF THERE IS A DUPLICATE PROFILE ALREADY IN THE DATABASE
    public boolean checkDuplicate(TravProf profile)
     {
         //CHECKS INPUT PROFILE WITH ALL OTHER PROFILES IN THE DATABASE
         TravProf first = database.findFirstProfile();
         if (first == null) { return false; }
         if (compareProfiles(first, profile))
         {
                 System.out.println("\nThis profile is already within the database.\n");
                 return true;
         }
         TravProf prof = database.findNextProfile();
         while (prof != null)
         {
             if (compareProfiles(prof, profile))
             {
                 System.out.println("\nThis profile is already within the database.\n");
                 return true;
             }
             prof = database.findNextProfile();
         }

         return false;
     }
    //COMPARES TRAVELER ID, FIRST NAME AND LAST NAME TO CHECK FOR SIMILARITIES
    //THIS WAY DELETING PROFILES IS EASIER (ONLY ONE UNIQUE NAME PER TRAVELER ID)
     public boolean compareProfiles(TravProf prof1, TravProf prof2)
     {
         //TRUE MEANS THEY ARE THE SAME
         //FALSE MEANS THEY ARE DIFFERENT
         //right now we have this checking ID/firstname/lastname but to delete u only need ID and last name

         return prof1.getTravAgentID().equals(prof2.getTravAgentID()) &&
                 prof1.getLastName().equals(prof2.getLastName());
     }


    //DELETES A TRAVPROF FROM DATABASE OF THE CURRENT SESSION.
    // TO CEMENT THE DELETION IN THE FILE AS WELL, WRITE TO FILE AFTER PROFILE IS DELETED.
    public void deleteTravProf() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the last name: ");
        String lastname = sc.nextLine();
        if (database.deleteProfile(ID, lastname)) {
            System.out.println("Your profile was deleted.");
        } else {
            System.out.println("Profile was not found.");
        }
    }

    //GIVEN A LAST NAME VIA PROMPT, A TRAVPROF IS FOUND IF LAST NAME AND ID MATCHES UP. IF NOT, IT RETURNS
    //A STATEMENT SAYING SO.
    //RECEIVING AN OUTPUT ONLY SAYING "PROFILE CANNOT BE FOUND" MEANS THERE EXISTS NO PROFILE WITH THAT LAST NAME.
    //RECEIVING AN OUTPUT OF "INSUFFICIENT ACCESS" AND "PROFILE CANNOT BE FOUND" MEANS
    // THERE EXISTS A PROFILE WITH THAT LAST NAME BUT YOU DO NOT HAVE THE CORRECT ID TO ACCESS IT.
    public void findTravProf(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the last name: ");
        String lastname = sc.nextLine();
        TravProf tprof = database.findProfile(ID, lastname);
        if (tprof != null) {
            this.displayTravProf(tprof);
        } else {
            System.out.println("Profile cannot be found.");
        }
    }

    //UPDATES A TRAVPROF IF LAST NAME IS FOUND AND YOU HAVE ACCESS TO IT (ID MATCHES)
    //RECEIVING AN OUTPUT ONLY SAYING "PROFILE CANNOT BE FOUND" MEANS THERE EXISTS NO PROFILE WITH THAT LAST NAME.
    //RECEIVING AN OUTPUT OF "INSUFFICIENT ACCESS" AND "PROFILE CANNOT BE FOUND" MEANS
    // THERE EXISTS A PROFILE WITH THAT LAST NAME BUT YOU DO NOT HAVE THE CORRECT ID TO ACCESS IT.
    public void updateTravProf() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the last name: ");
        String lastname = sc.nextLine();
        TravProf tprof = database.findProfile(ID, lastname);
        if (tprof != null) {
            this.displayTravProf(tprof);
        } else {
            System.out.println("Profile cannot be found.");
            return;
        }

        System.out.println("Please enter the number corresponding to the item you'd like to update: ");
        System.out.println("(1) Address");
        System.out.println("(2) Phone Number");
        System.out.println("(3) Trip Cost");
        System.out.println("(4) Travel Type");
        System.out.println("(5) Payment Type");
        System.out.println("(6) Doctor Name");
        System.out.println("(7) Doctor Phone Number");
        System.out.println("(8) Allergy Type");
        System.out.println("(9) Illness Type");

        int input = sc.nextInt();
        boolean choice = true;
        //CHECKS IF CORRECT INPUTS ARE MADE FOR SWITCH CASES. IF NOT, PROMPTS UNTIL CORRECT INPUT IS GIVEN
        while (choice){
            Scanner scan = new Scanner(System.in);
            switch (input) {
                case 1:
                    System.out.println("Enter a new address: ");
                    tprof.updateAddress(scan.nextLine());
                    choice = false;
                    break;
                case 2:
                    System.out.println("Enter a new phone number: ");
                    tprof.updatePhone(scan.nextLine());
                    choice = false;
                    break;
                case 3:
                    System.out.println("Enter a trip cost: ");
                    tprof.updateTripCost(scan.nextFloat());
                    choice = false;
                    break;
                case 4:
                    System.out.println("Enter a new travel type: ");
                    tprof.updateTravelType(scan.nextLine());
                    choice = false;
                    break;
                case 5:
                    System.out.println("Enter a new payment type: ");
                    tprof.updatePaymentType(scan.nextLine());
                    choice = false;
                    break;
                case 6:
                    System.out.println("Enter a new doctor name: ");
                    tprof.getMedCondInfo().setMdContact(scan.nextLine());
                    choice = false;
                    break;
                case 7:
                    System.out.println("Enter a doctor phone number: ");
                    tprof.getMedCondInfo().setMdPhone(scan.nextLine());
                    choice = false;
                    break;
                case 8:
                    System.out.println("Enter a new allergy type: ");
                    tprof.getMedCondInfo().setAlgType(scan.nextLine());
                    choice = false;
                    break;
                case 9:
                    System.out.println("Enter a new illness type: ");
                    tprof.getMedCondInfo().setIllType(scan.nextLine());
                    choice = false;
                    break;
                default:
                    System.out.println("Please enter a valid choice.");
            }

        }
    }


    //GETS ALL VALUES AND PRINTS THEM OUT
    public void displayTravProf(TravProf tp) {
        System.out.println("Travel Agent ID: " + tp.getTravAgentID());
        System.out.println("First Name: " + tp.getFirstName());
        System.out.println("Last Name: " + tp.getLastName());
        System.out.println("Address: " + tp.getAddress());
        System.out.println("Phone Number: " + tp.getPhone());
        System.out.println("Trip Cost: " + tp.getTripCost());
        System.out.println("Travel Type: " + tp.getTravelType());
        System.out.println("Payment Type: " + tp.getPaymentType());
        System.out.println("Doctor Name: " + tp.getMedCondInfo().getMdContact());
        System.out.println("Doctor Phone Number: " + tp.getMedCondInfo().getMdPhone());
        System.out.println("Allergy Type: " + tp.getMedCondInfo().getAlgType());
        System.out.println("Illness Type: " + tp.getMedCondInfo().getIllType());
        System.out.println("\n");
    }

    //DISPLAYS ALL TRAVELER PROFILES ASSOCIATED WITH THE NEWLY INPUT ID
    public void displayAllTravProf() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your ID: ");
        String verID = sc.nextLine();

        //KEEPS TRACK OF PROFILES ASSOCIATED WITH THIS ID
        int count = 0;
        TravProf first = database.findFirstProfile();
        if (first == null) {
            System.out.println("There are no profiles.");
            return;
        }
        //CHECKS IF TRAVELAGENT IDS MATCH UP AND ONLY DISPLAYS FIRST PROFILE IF THEY DO
        if (first.getTravAgentID().equals(verID)) {
            System.out.println();
            displayTravProf(first);
            count++;
        }
        TravProf cur = database.findNextProfile();

        //CHECKS IF TRAVELAGENT IDS MATCH UP AND ONLY DISPLAYS PROFILES IF THEY DO
        while (cur != null) {
            if (cur.getTravAgentID().equals(verID)) {
                System.out.println();
                displayTravProf(cur);
                count++;
            }
            cur = database.findNextProfile();

        }
        //IF THERE ARE NO ASSOCIATED PROFILES, THIS MESSAGE WILL PRINT
        if (count == 0)
        {
            System.out.println("There are no profiles associated with this ID.");
        }
    }

    //ALL CHANGES (WHETHER PROFILES ARE ADDED, EDITED, OR DELETED) ARE SAVED TO THE FILE
    public void writeToDB() throws IOException {
        database.writeAllTravProf(filename);
        System.out.println("Changes were saved to file successfully.");
    }

    //INITIALIZES DATABASE. CHECKS IF FILE IS EMPTY FIRST, IF NOT, THEN USES DB METHOD TO INITIALIZE THE DATABASE
    public void initDB() throws IOException, ClassNotFoundException {
        if (newFile.length() == 0)
        {
            System.out.println("File is empty.");
        }
        else {
            database.initializeDatabase(filename);
            System.out.println("Database is initialized.");
        }
    }

    public static void main(String [] args) throws IOException, ClassNotFoundException {
        //YOU CAN CHANGE THE NAME OF THIS FILE TO FIT WHATEVER FILE YOU WANT TO WRITE TO
        TravProfInterface tpi = new TravProfInterface("TravelerDataBase.dat");
        boolean state = true;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Integrated Travel System");
        System.out.println("Please enter your ITS ID: ");
        //ID IS INITIALIZED HERE
        ID = sc.nextLine();

        //STATEMENTS INSIDE GETUSERCHOICE WILL EXIT THE PROGRAM IF THAT IS THE DESIRED CHOICE
        //GETUSERCHOICE WILL RUN UNTIL IT IS EXITED
        while (state) {
            tpi.getUserChoice();
        }
    }
}

