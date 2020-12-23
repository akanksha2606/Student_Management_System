import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.*;

import javax.swing.JFrame;

class Registration_Frame extends Frame implements ItemListener,ActionListener
{
	Label lheading,lname,lroll,ldob,lgender,laddress,lemail,lcont1,lcont2;
	TextField tname,troll,taddress,temail,tcont1,tcont2;
	String sname,sroll,sdob,sgender,saddress,semail,scont1,scont2,pstring,mstring;
	Choice cdate,cmonth,cyear;
	CheckboxGroup cbggender;
	Checkbox cmale,cfemale,ctrans;
	Button bsubmit;
	Boolean File_Name,result_cont1,result_cont2,result_email;
	String /* regex="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",*//* recontact1="^\\d{10}$",*/recontact2="^\\d{10}$",valid_email,valid_cont1_no,valid_cont2_no;             
	Matcher mt1,mt2;
	
	Registration_Frame()
	{
		setLayout(null);
		setSize(2000,2000);
		setFont(new Font("Times New Roman",Font.BOLD,25));
		
		//heading...
				lheading=new Label("NEW REGISTRATION FORM");
				lheading.setFont(new Font("Helvetica",Font.BOLD,27));
				lheading.setBounds(750,100,750,40);
				add(lheading);
				
				
		//name structure
				lname=new Label("NAME:");
				lname.setBounds(300,350,400,25);
				add(lname);
				tname=new TextField(50);
				tname.setBounds(700,350,500,35);
				add(tname);
				
		//Roll_No structure
				lroll=new Label("Roll No.:");
				lroll.setBounds(300,390,400,25);
				add(lroll);
				troll=new TextField(50);
				troll.setBounds(700,390,500,35);
				add(troll);
				setLayout(null);
				
		//Date of Birth Structure
				ldob=new Label("Date of Birth:");
				ldob.setBounds(300,430,400,25);
				add(ldob);
				
				cdate=new Choice();        //date
				 cdate.add("DD");
				
			    for(int i=1;i<=31;i++)
				 {
			       cdate.add(" "+i);
				 }
			    cdate.setBounds(700,430,100,30);
			    add(cdate);
			    cdate.addItemListener(this);
			    
				cmonth=new Choice();                //month
				cmonth.add("MM");
			    for(int i=1;i<+12;i++)
				 {
					 cmonth.add(" "+i);
				 }
			    cmonth.setBounds(820,430,100,30);
			    add(cmonth);
			    cmonth.addItemListener(this);
			    
				cyear=new Choice();                //year
			    cyear.add("YYYY");
				for(int i=1975;i<=2020;i++)
			      {
				    cyear.add(" "+i);
				  }
				cyear.setBounds(940,430,100,30);
				add(cyear);
				cyear.addItemListener(this); 	
				
		//Gender
			lgender=new Label("Gender:");
				lgender.setBounds(300,470,100,25);
				add(lgender);
				cbggender=new CheckboxGroup();
				cmale=new Checkbox("Male",true,cbggender);
				cmale.setBounds(700,470,100,35);
				add(cmale);
				cmale.addItemListener(this);
				cfemale=new Checkbox("Female",false,cbggender);
				cfemale.setBounds(700,510,100,35);
				add(cfemale);
				cfemale.addItemListener(this);
				ctrans=new Checkbox("Transgender",false,cbggender);
				ctrans.setBounds(700,550,200,35);
				add(ctrans);
				ctrans.addItemListener(this);
				
		//Address Structure
				laddress=new Label("Address:");
				laddress.setBounds(300,600,400,25);
				add(laddress);
				taddress=new TextField(50);
				taddress.setBounds(700,600,500,35);
				add(taddress);
					
		//Email Structure
				lemail=new Label("Email:");
				lemail.setBounds(300,640,400,25);
				add(lemail);
				temail=new TextField(50);
				temail.setBounds(700,640,500,35);
				add(temail);
				String text=temail.getText(); 
				
		//contact structure
				lcont1=new Label("Contact :");
				lcont1.setBounds(300,680,400,25);
				add(lcont1);
				tcont1=new TextField(50);
				tcont1.setBounds(700,680,500,35);
				add(tcont1);
				
				lcont2=new Label("Alternate Contact No. :");
				lcont2.setBounds(300,720,400,25);
				add(lcont2);
				tcont2=new TextField(50);
				tcont2.setBounds(700,720,500,35);
				add(tcont2);
					
		//Submit button		
				bsubmit=new Button("Submit");
				bsubmit.setBounds(500,850,200,50);
				add(bsubmit);
				bsubmit.addActionListener(this);
	}

	
	public void itemStateChanged(ItemEvent ie)
	 {
			 repaint();
	 }
	
	public void actionPerformed(ActionEvent ae)
	{  
		sname=tname.getText();
		sroll=troll.getText();
		sdob=""+cdate.getSelectedItem()+"/ "+cmonth.getSelectedItem()+"/"+cyear.getSelectedItem();
		sgender=cbggender.getSelectedCheckbox().getLabel();
		saddress=taddress.getText();
		semail=temail.getText();
		scont1=tcont1.getText();
		scont2=tcont2.getText(); 
		Dialog_submit ds1;
		Dialog_email de1;
		Dialog_contact1 dc1;
		Dialog_contact2 dc2;
		
		if(ae.getActionCommand()=="Submit")
		{
			try
			{
		       File f1=new File("F:\\file handling\\Student_Management_System_Data\\"+sroll+".txt");
		       File_Name=f1.createNewFile();
	           FileWriter writer = new FileWriter(f1);
		       BufferedWriter buffer = new BufferedWriter(writer); 
		     
		       buffer.write(  " Name            :"+sname);
		       buffer.write("\n Roll_no.        :"+sroll);
		       buffer.write("\n Date of Birth   :"+sdob);
		       buffer.write("\n Gender          :"+sgender);
		       buffer.write("\n Address         :"+saddress);
		       buffer.write("\n Email           :"+semail);
		       buffer.write("\n Contact No.     :"+scont1);
		       buffer.write("\n Alternate No.   :"+scont2);
		       buffer.close();
		       
		  //email Regex     
		       Pattern pt1=Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$",Pattern.CASE_INSENSITIVE);
		       Matcher mt1=pt1.matcher(semail);
		       result_email=mt1.find();
		      
		  //contact 1 regex     
		      Pattern pt2=Pattern.compile("^\\d{10}$");
		      Matcher mt2=pt2.matcher(scont1);  
		      result_cont1=mt2.find();
		      
	      //contact 2 regex
		      Pattern pt3=Pattern.compile("^\\d{10}$");
		      Matcher mt3=pt3.matcher(scont2);
		      result_cont2=mt3.find();
		      
			}  
			catch(Exception e) 
			{
				System.out.println("Exception Occurs");
			}
		}
		
		if(ae.getActionCommand()=="Submit")
		{
			//valid email checker
			 if(result_email==true)
		       {
				 valid_email="Yes";
				 
		       }
		       else
		       {
		    	   valid_email="No";
		       }
			//valid contact1 number checker
			 if(result_cont1==true)
			 {
				 valid_cont1_no="Yes";
			 }
			 else
			 {
			     valid_cont1_no="No";
			 }
			 //valid contact2 number checker
			 if(result_cont2==true)
			 {
				 valid_cont2_no="Yes";
			 }
			 else
			 {
				 valid_cont2_no="No";
			 }
			 
			 if(valid_email=="No")                     //valid email dialog box
			 {
				 de1=new Dialog_email();
				 de1.setVisible(true); 
			 }
			 if(valid_cont1_no=="No")                 //valid contact1 dialog box
			 {
				 dc1=new Dialog_contact1();
				 dc1.setVisible(true);
			 }
			 if(valid_cont2_no=="No")                 //valid contact2 dialog box
			 {
				 dc2=new Dialog_contact2();
				 dc2.setVisible(true); 
			 }
		     else
			 {
		    	 ds1=new Dialog_submit();
				 ds1.setVisible(true); 
			 }
			
		}
	
   }
	
}

//Submit button dialog box
class Dialog_submit extends WindowAdapter 
{
	Dialog d1;
	Label Labell1;
	
	Dialog_submit()
	{
		setFont(new Font("Calibri",Font.BOLD,25));
		d1=new Dialog(null, true);
		Labell1=new Label("YOUR DATA IS SUCCESSFULLY SUBMITTED!!!");
		d1.add(Labell1);
		d1.addWindowListener(this);
		d1.pack();
		d1.setLocationRelativeTo(null);
		d1.setLocation(new Point(500,500));
		d1.setSize(600,200);
		d1.setVisible(true);
	}
	private void setFont(Font font) {
		// TODO Auto-generated method stub
		
	}
	public void setVisible(boolean b)
	{
		// TODO Auto-generated method stub
	}
	public void windowClosing(WindowEvent we)
	{
	d1.setVisible(false);
	}
}

//Email incorrect dialog box
class Dialog_email extends WindowAdapter
{
	Dialog d2;
	Label Labell2;
	Dialog_email()
	{
		setFont(new Font("Calibri",Font.BOLD,25));
		d2=new Dialog(null,true);
		Labell2=new Label("YOU HAVE NOT ENTERED PROPER EMAIL ID");
		d2.add(Labell2);
		d2.addWindowListener(this);
		d2.pack();
		d2.setLocationRelativeTo(null);
		d2.setLocation(new Point(500,500));
		d2.setSize(600,200);
		d2.setVisible(true);
	}
	private void setFont(Font font) {
		// TODO Auto-generated method stub
		
	}
	public void setVisible(boolean b)
	{
		// TODO Auto-generated method stub
	}
	public void windowClosing(WindowEvent we)
	{
	d2.setVisible(false);
	}
}

//Contact 1 incorrect dialog box
class Dialog_contact1 extends WindowAdapter
{
	Dialog d3;
	Label Labell3;
	Dialog_contact1()
	{
		setFont(new Font("Calibri",Font.BOLD,25));
		d3=new Dialog(null,true);
		Labell3=new Label("YOU HAVE NOT ENTERED PROPER CONTACT NUMBER");
		d3.add(Labell3);
		d3.addWindowListener(this);
		d3.pack();
		d3.setLocationRelativeTo(null);
		d3.setLocation(new Point(500,500));
		d3.setSize(600,200);
		d3.setVisible(true);
	}
	private void setFont(Font font) {
		// TODO Auto-generated method stub
		
	}
	public void setVisible(boolean b) 
	{
		// TODO Auto-generated method stub
	}
	public void windowClosing(WindowEvent we)
	{
	d3.setVisible(false);
	}
}
//Contact 2 incorrect dialog box
class Dialog_contact2 extends WindowAdapter
{
	Dialog d4;
	Label Labell4;
	Dialog_contact2()
	{
		setFont(new Font("Calibri",Font.BOLD,45));
		d4=new Dialog(null,true);
		Labell4=new Label("YOU HAVE NOT ENTERED PROPER ALTERNATE CONTACT NUMBER");
		d4.add(Labell4);
		d4.addWindowListener(this);
		d4.pack();
		d4.setLocationRelativeTo(null);
		d4.setLocation(new Point(500,500));
		d4.setSize(600,200);
		d4.setVisible(true);
	}
	private void setFont(Font font) {
		// TODO Auto-generated method stub
		
	}
	public void setVisible(boolean b)
	{
		// TODO Auto-generated method stub
	}
	public void windowClosing(WindowEvent we)
	{
	d4.setVisible(false);
	}
}

class Show_Frame extends Frame implements ActionListener
{
	Label show_Label;
	TextField show_roll_no;
	Button Bshow;
	
	Show_Frame()
	{
		setLayout(null);
		setFont(new Font("Times New Roman",Font.BOLD,25));
		setSize(2100,2100);
		show_Label=new Label("ENTER YOUR ROLL NO :");
		show_Label.setBounds(600,300,300,30);
		add(show_Label);
		show_roll_no=new TextField(100);
		show_roll_no.setBounds(900,300,500,40);
		add(show_roll_no);
		Bshow=new Button("SHOW FILE");
		Bshow.setBounds(800,400,150,75);
		add(Bshow);
		Bshow.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String file_name;
		File file_to_show;
		file_not_exist sf_not_exist;
		if(ae.getActionCommand()=="SHOW FILE")
		{
			try
			{
				file_name=show_roll_no.getText();
				file_to_show=new File("F:\\file handling\\Student_Management_System_Data\\"+file_name+".txt");
				Desktop d=Desktop.getDesktop();
				if(file_to_show.exists())
				{
				  d.open(file_to_show);
				}
				else
				{
					sf_not_exist=new file_not_exist();
					sf_not_exist.setVisible(true);
				}
			}
			catch(Exception e)
			{
				System.out.println("not found");
			}
		}
	}
}

class file_not_exist extends WindowAdapter
{
	Dialog d5;
	Label Labell5;
	Button bok;
    file_not_exist()
	{
    	setFont(new Font("Calibri",Font.BOLD,45));
		d5=new Dialog(null,true);
		Labell5=new Label("FILE NAME DOES NOT EXISTS");
		d5.add(Labell5);
		d5.addWindowListener(this);
		d5.pack();
		d5.setLocationRelativeTo(null);
		d5.setLocation(new Point(500,500));
		d5.setSize(500,200);
		d5.setVisible(true);
	}
	
	private void setFont(Font font) {
		// TODO Auto-generated method stub
		
	}

	public void setVisible(boolean b)
	{
		// TODO Auto-generated method stub
	}
	public void windowClosing(WindowEvent we)
	{
	    d5.setVisible(false);
	}
}

class Delete_file_frame extends Frame implements ActionListener
{
	Label Delete_file_Label;
	TextField Delete_file;
	Button Delete_file_button;
	Delete_file_frame()
	{
		setLayout(null);
		setFont(new Font("Times New Roman",Font.BOLD,25));
		setSize(2100,2100);
		Delete_file_Label=new Label("ENTER FILE NAME :");
		Delete_file_Label.setBounds(600,300,300,30);
		add(Delete_file_Label);
		Delete_file=new TextField(100);
		Delete_file.setBounds(900,300,500,40);
		add(Delete_file);
		Delete_file_button=new Button("DELETE FILE");
		Delete_file_button.setBounds(800,400,200,75);
		add(Delete_file_button);
		Delete_file_button.addActionListener(this);
	}
	
public void actionPerformed(ActionEvent ae)
{
	String file_name_to_delete;
	File f;
	Delete_file_after dafter;
	file_not_exist fn_not_exists;
	if(ae.getActionCommand()=="DELETE FILE")
	 {
		try
		{
			file_name_to_delete=Delete_file.getText();
			f=new File("F:\\file handling\\Student_Management_System_Data\\"+file_name_to_delete+".txt");
				if(f.exists())
				{
				   f.delete();
				   dafter=new Delete_file_after();
				   dafter.setVisible(true);
				}
			    else
				{
			      fn_not_exists=new file_not_exist();
				  fn_not_exists.setVisible(true);
				}
		}
				catch(Exception e)
				{
					System.out.println("Error occurs");
				}
				
			}
	}
}

class Delete_file_after extends WindowAdapter 
{
	Dialog d6;
	Label Labell;
	Button bok;
	Delete_file_after()
	{
		setFont(new Font("Calibri",Font.BOLD,35));
		d6=new Dialog(null,true);
		Labell=new Label("YOUR DATA IS SUCCESSFULLY DELETED!!!");
		d6.add(Labell);
		d6.addWindowListener(this);
		d6.pack();
		d6.setLocationRelativeTo(null);
		d6.setLocation(new Point(500,500));
		d6.setSize(600,200);
		d6.setVisible(true);
	}
	
	private void setFont(Font font) {
		// TODO Auto-generated method stub
		
	}

	public void setVisible(boolean b)
	{
		// TODO Auto-generated method stub
	}
	public void windowClosing(WindowEvent we)
	{
	    d6.setVisible(false);
	}
	
	}


// Edit information 

class Update_Page_Starting extends Frame implements ActionListener
{
	Label label_roll_no;
	TextField Text_roll_no;
	Button Bnext;
	Edit_Frame fedit;
	file_not_exist fnotExists;
	
	Update_Page_Starting()
	{
		setLayout(null);
		setFont(new Font("Calibri",Font.BOLD,35));
		setSize(2500,2500);
		label_roll_no=new Label("ENTER THE NAME OF OF THE FILE");
		label_roll_no.setBounds(650,400,600,40);
		add(label_roll_no);
		
		Text_roll_no=new TextField(100);
		Text_roll_no.setBounds(785,450,250,40);
		add(Text_roll_no);
		
		Bnext=new Button("NEXT");
		Bnext.setBounds(825,550,100,50);
		add(Bnext);	
		Bnext.addActionListener(this);
	}
	
  public void actionPerformed(ActionEvent ae)
  {
	  File f;
	  String File_name_to_update;
	  if(ae.getActionCommand()=="NEXT")
	  {
		  try
		  {
			  File_name_to_update=Text_roll_no.getText();
			 f=new File("F:\\file handling\\Student_Management_System_Data\\"+File_name_to_update+".txt");
			 if(f.exists())
			 {
				 fedit=new Edit_Frame(File_name_to_update);
				 fedit.setVisible(true);
				
			 }
			 else
			 {
				 fnotExists=new file_not_exist();
				 fnotExists(true);
			 }
		  }
		  catch(Exception e)
		  {
			  
		  }
	  }
  }

private void fnotExists(boolean b) {
	// TODO Auto-generated method stub
	
}
}


class Edit_Frame extends Frame implements ItemListener,ActionListener
{
	Label heading_label;
	Checkbox check_name,check_roll_no,check_dob,check_gender,check_address,check_email,check_contact1,check_contact2;
	String str_che_name,str_che_roll_no,str_che_dob,str_che_gender,str_che_address,str_che_email,str_che_cont1,str_che_cont2;
	Button Go_to_update;
	Update_Frame fupdate;
	String File_name;
	
	Edit_Frame(String fn)
	{
		setLayout(null);
		setSize(2500,2500);
		setFont(new Font("Times New Roman",Font.BOLD,25));
		File_name=fn;
		
		
		heading_label=new Label("CHOOSE THE OPTIONS YOU WANT TO EDIT");
		heading_label.setBounds(600,200,600,30);
		add(heading_label);
		
		//checkboxes
		check_name=new Checkbox("NAME");
		check_name.setBounds(700,300,500,30);
		add(check_name);
	//	check_name.addItemListener(this);
		
		
		check_roll_no=new Checkbox("ROLL_NO");
		check_roll_no.setBounds(700,350,500,30);
		add(check_roll_no);
//		check_roll_no.addItemListener(this);
		
		
		check_dob=new Checkbox("DATE_OF_BIRTH");
		check_dob.setBounds(700,400,500,30);
		add(check_dob);
//		check_dob.addItemListener(this);
		
		
		check_gender=new Checkbox("GENDER");
		check_gender.setBounds(700,450,500,30);
		add(check_gender);
//		check_gender.addItemListener(this);
		
		
		check_email=new Checkbox("EMAIL");
		check_email.setBounds(700,500,500,30);
		add(check_email);
//		check_email.addItemListener(this);
		
		
		check_contact1=new Checkbox("CONTACT_NO");
		check_contact1.setBounds(700,550,500,30);
		add(check_contact1);
//		check_contact1.addItemListener(this);
		
		
		check_contact2=new Checkbox("ALERNATE_CONTACT_NO");
		check_contact2.setBounds(700,600,500,30);
		add(check_contact2);
	//	check_contact2.addItemListener(this);
		
		
		Go_to_update=new Button("GO TO UPDATE FORM");
		Go_to_update.setBounds(850,700,300,60);
		add(Go_to_update);
		Go_to_update.addActionListener(this);
		
	
	}

 public void actionPerformed(ActionEvent ae)
  {
	if(ae.getActionCommand()=="GO TO UPDATE FORM")
	{
		
		fupdate=new Update_Frame(str_che_name,str_che_roll_no,str_che_dob,str_che_gender,str_che_address,str_che_email,str_che_cont1,str_che_cont2,File_name);
		fupdate.setVisible(true);
	}
  }


 @Override
 public void itemStateChanged(ItemEvent arg0) 
 {
	// TODO Auto-generated method stub
	
 }

}

class Update_Frame extends Frame implements ItemListener,ActionListener
{ 
	TextField field_name,field_roll_no,field_dob,field_gender,field_address,field_email,field_cont1,field_cont2;
	Label fill_name,fill_roll_no,fill_dob,fill_gender,fill_address,fill_email,fill_cont1,fill_cont2;
	Choice choice_date,choice_month,choice_year;
	Checkbox check_male,check_female,check_trans;
	CheckboxGroup Box_gender;
	Button submit;
	String Main_File_Name;
	 
	String str;
	FileReader reader;
	BufferedReader buffer1;
	
	
	Update_Frame(String name,String roll_no,String dob,String gender,String address,String email,String cont1,String cont2,String file_name_update)
	{
		setSize(2000,2000);
		setFont(new Font("Helvetica",Font.BOLD,20));
		setLayout(null);
		 Main_File_Name=file_name_update;
			
	
		
	//*******************UPDATE NAME**************************
		
		fill_name=new Label("NAME:");
		fill_name.setBounds(400,250,320,35);
		add(fill_name);
		field_name=new TextField(500);
		field_name.setBounds(730,250,500,40);
		add(field_name);
		
   //******************UPDATE ROLL_NO**************************	
		
		fill_roll_no=new Label("ROLL_NO:");
		fill_roll_no.setBounds(400,295,320,35);
		add(fill_roll_no);
		field_roll_no=new TextField(500);
		field_roll_no.setBounds(730,295,500,40);
		add(field_roll_no);
		
   //*********************UPDATE DATE OF BIRTH******************
		
		//LABEL
		fill_dob=new Label("DATE OF BIRTH");
		fill_dob.setBounds(400,345,320,35);
		add(fill_dob);
		
		//CHOICES
		choice_date=new Choice();        //date
		 choice_date.add("DD");
		
	    for(int i=1;i<=31;i++)
		 {
	       choice_date.add(" "+i);
		 }
	    choice_date.setBounds(730,345,100,30);
	    add(choice_date);
	    choice_date.addItemListener(this);
	    
		choice_month=new Choice();                //month
		choice_month.add("MM");
	    for(int i=1;i<+12;i++)
		 {
			 choice_month.add(" "+i);
		 }
	    choice_month.setBounds(830,345,100,30);
	    add(choice_month);
	    choice_month.addItemListener(this);
	    
		choice_year=new Choice();                //year
	    choice_year.add("YYYY");
		for(int i=1975;i<=2020;i++)
	      {
		    choice_year.add(" "+i);
		  }
		choice_year.setBounds(930,345,100,30);
		add(choice_year);
		choice_year.addItemListener(this); 	
	
    //*********************UPDATE GENDER*******************************************
		//LABEL
		fill_gender=new Label("GENDER:");
		fill_gender.setBounds(400,385,320,35);
		add(fill_gender);
		
		//GENDER
		Box_gender=new CheckboxGroup();
		check_male=new Checkbox("Male",true,Box_gender);
		check_male.setBounds(730,395,100,40);
		add(check_male);
		check_male.addItemListener(this);
		check_female=new Checkbox("Female",false,Box_gender);
		check_female.setBounds(730,445,100,40);
		add(check_female);
		check_female.addItemListener(this);
		check_trans=new Checkbox("Transgender",false,Box_gender);
		check_trans.setBounds(730,495,200,40);
		add(check_trans);
		check_trans.addItemListener(this);
		
//*******************UPDATE ADDRESS*****************************************************
		
      	fill_address=new Label("ADDRESS:");
		fill_address.setBounds(400,540,320,35);
		add(fill_address);
		field_address=new TextField(500);
		field_address.setBounds(730,540,500,40);
		add(field_address);

//************************UPDATE EMAIL***********************************************
		
		fill_email=new Label("EMAIL:");
		fill_email.setBounds(400,595,320,35);
		add(fill_email);
		field_email=new TextField(500);
		field_email.setBounds(730,590,500,40);
		add(field_email);

//***********************UPDATE CONTACT NO*********************************************
		
		fill_cont1=new Label("CONTACT NO.:");
		fill_cont1.setBounds(400,645,320,35);
		add(fill_cont1);
		field_cont1=new TextField(500);
		field_cont1.setBounds(730,640,500,40);
		add(field_cont1); 
	
//******************************UPDATE ALTERNATE CONTACT NO*********************************
		
		fill_cont2=new Label("ALTERNATE CONTACT NO.:");
		fill_cont2.setBounds(400,690,320,35);
		add(fill_cont2);
		field_cont2=new TextField(500);
		field_cont2.setBounds(730,690,500,40);
		add(field_cont2); 
		
		submit=new Button("SUBMIT CHANGES");
		submit.setBounds(750,750,100,50);	
		add(submit);
		
		
		try
		{
			
			reader=new FileReader("F:\\file handling\\Student_Management_System_Data\\"+Main_File_Name+".txt");
			buffer1=new BufferedReader(reader);
			str=buffer1.readLine();
			while(str!=null)
			{
		/*		if(str.contains("Name       :"))
				{
					String []words=str.split(":",2);
					// field_name.setText(words[1]);
				}*/
			}
			str=buffer1.readLine();
   		 System.out.println(str);
   		 String []words= str.split(":",2);
   		System.out.println(words);
			buffer1.close();
		}
		catch(Exception e)
		{
			 System.out.println("ERROR OCCUR");
		}

       
	}
	public void ItemStateChanged(ItemEvent ie)
	{
		
		
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getActionCommand()=="SUBMIT CHANGES")
		{
			
		}       
	}

}
public class Project_Student_Management_System extends Applet implements ActionListener
{
	   Button registration,edit,delete,show;
	   Registration_Frame freg;
	   Show_Frame fshow;
	   Delete_file_frame fdelete;
	   Update_Page_Starting Update_frame;
	   
	   public void init()
	      {
		   setSize(2000,2000);
		   setFont(new Font("Helvetica",Font.BOLD,20));
		   setLayout(null);
		   freg=new Registration_Frame();
		   fshow=new Show_Frame();
		   fdelete=new Delete_file_frame();
		   Update_frame=new Update_Page_Starting();
		   
		   setBackground(Color.black);
		   
		   // New Registration button
		   registration=new Button("NEW REGISTRATION");
		   registration.setBounds(600,200,220,120);
		   registration.setBackground(Color.yellow);
		   add(registration);
		   registration.addActionListener(this);
		   
		  //Edit Info button
		   edit=new Button("EDIT INFORMATION");
		   edit.setBounds(850,200,220,120);
		   edit.setBackground(Color.yellow);
		   add(edit);
		   edit.addActionListener(this);
		   
		   //Delete File info
		   delete=new Button("DELETE INFORMATION");
		   delete.setBounds(600,450,220,120);
		   delete.setBackground(Color.yellow);
		   add(delete);
		   delete.addActionListener(this);
		   
		   //Show File Info
		   show=new Button("SHOW INFORMATION");
		   show.setBounds(850,450,220,120);
		   show.setBackground(Color.yellow);
		   add(show);
		   show.addActionListener(this);
		  }
	   
	   public void actionPerformed(ActionEvent ae)
	    {
		   if(ae.getActionCommand()=="NEW REGISTRATION")
		   {
			  freg.setVisible(true); 
			   
		   }
		   else if(ae.getActionCommand()=="EDIT INFORMATION")
		   {
			   Update_frame.setVisible(true);
		   }
		   else if(ae.getActionCommand()=="SHOW INFORMATION")
		   {
			   fshow.setVisible(true);
		   }
		   else if(ae.getActionCommand()=="DELETE INFORMATION")
		   {
			   fdelete.setVisible(true);;
		   }
	    
	    }
  }
	

