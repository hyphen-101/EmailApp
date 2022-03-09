# EmailApp
A Java Ant application that will send emails to a group and manage the emails such as adding new recipients, deleting the group list, creating the group list and sending emails to the group list.
For the application to work you need to consider the following:
Gmail email address,
Gmail account password,
path of the text file from the recipient list,
path of the file you want to attach to the mail.
Download Jars :
JavaMail API,
Java Activation Framework (JAF),
Your SMTP server details (in this example i used Google's SMTP you can set it up with your servers SMTP if you'd like)
Once you check on all the components list..
you have just to copy paste your Accounnt and Password on Java Interface named IAccesoDatos
then set the path to append files that will go in your mail in Java Obj AccesoDatosImple line 149.
and that's pretty much it
if you want to change the path of the list of recepients go to Java Interface ICatalogoRecipiente (optional)
