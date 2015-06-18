/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMS;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author Daan
 */

//@MessageDriven(mappedName="jms/vpsystem/queue")
public class JMSBean /*implements MessageListener*/{

    /*@Resource(mappedName="ConnectionFactory")
    private ConnectionFactory factory;
    
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage msg = (TextMessage)message;
            if (msg.getText().equals("Autogegevens")){
                Queue queue = (Queue)msg.getJMSReplyTo();
                Connection connect = factory.createConnection();
                Session session = connect.createSession(true,0);
                MessageProducer sender = session.createProducer(queue);
                ObjectMessage objmsg = session.createObjectMessage();
                //objmsg.setObject(new Object());
                sender.send(objmsg);
                connect.close();
            }
        } catch (JMSException ex) {
            Logger.getLogger(JMSBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
}
