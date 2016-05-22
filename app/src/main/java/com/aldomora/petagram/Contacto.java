package com.aldomora.petagram;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Contacto extends AppCompatActivity {

    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    String msg, email, subject;
    TextInputLayout nombreForm,emailForm,msgForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        context = this;
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nombreForm = (TextInputLayout) findViewById(R.id.nombreForm);
        emailForm = (TextInputLayout) findViewById(R.id.emailForm);
        msgForm = (TextInputLayout) findViewById(R.id.msgForm);
        Button enviar = (Button) findViewById(R.id.btnEnviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailForm.getEditText().getText().toString();
                subject = "Comentario de " + nombreForm.getEditText().getText().toString();
                msg = String.format("Nombre:<br />%s<br /><br />Email:<br />%s<br /><br />Mensaje:<br />%s",nombreForm.getEditText().getText().toString(),emailForm.getEditText().getText().toString(),msgForm.getEditText().getText().toString());
                Properties props = new Properties();
                props.put("mail.smtp.starttls.enable","true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "587");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "587");

                session = Session.getDefaultInstance(props, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("amhtest4@gmail.com", "cuentadeprueba");
                    }
                });

                pdialog = ProgressDialog.show(context, "", "Enviando mensaje...", true);

                RetreiveFeedTask task = new RetreiveFeedTask();
                task.execute();
            }

        });
    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                javax.mail.Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(email));
                message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse("aldo.mor@gmail.com"));
                message.setSubject(subject);
                message.setContent(msg, "text/html; charset=utf-8");
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            nombreForm.getEditText().setText("");
            emailForm.getEditText().setText("");
            msgForm.getEditText().setText("");
            Toast.makeText(getApplicationContext(), "Mensaje enviado", Toast.LENGTH_LONG).show();
        }
    }
}