/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sha1;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class frmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form frmPrincipal
     */
    public frmPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCadena = new javax.swing.JTextField();
        btnSHA1 = new javax.swing.JButton();
        txtSHA1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSHA1.setText("SHA-1");
        btnSHA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSHA1ActionPerformed(evt);
            }
        });

        txtSHA1.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(btnSHA1)
                        .addGap(0, 171, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSHA1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCadena))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCadena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSHA1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSHA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSHA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSHA1ActionPerformed
        // TODO add your handling code here:
        txtSHA1.setText(SHA1(txtCadena.getText()));
    }//GEN-LAST:event_btnSHA1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPrincipal().setVisible(true);
            }
        });
    }

    private String Completar(String cadena,int bits)
    {
        String nuevo=cadena;
        while(nuevo.length()<bits)
            nuevo="0"+nuevo;
        return nuevo;
    }
    private String Rellenar(String cadena)   
    {
        //Cambiar a forma binaria
        String c = "";
        for(int i=0; i<cadena.length();i++)
        {
            c+=Completar(Integer.toBinaryString( cadena.charAt(i)),8);
        }
        //Completar para que sea multiplo de 512
        int longitud = c.length();
        c+="1";
        while((c.length()+64)%512!=0)
            c+="0";
        c+=Completar(Integer.toBinaryString(longitud),64);
        return c;
    }
    private ArrayList<Integer> Bloques(String cadena)
    {
        String c = Rellenar(cadena);
        ArrayList<Integer> bloques = new ArrayList<Integer>();
        for(int i=0;i<c.length();i+=32)
        {
            char[] sub = new char[32];
            c.getChars(i, i+32, sub, 0);
            Integer n = StringBinaryInteger(sub);
            bloques.add(n);
        }
        return bloques;
    }
    private int StringBinaryInteger(char[] cadena)    
    {
        char[] v = new char[cadena.length-1];
        for(int i=1;i<cadena.length;i++)
            v[i-1] = cadena[i];
        int valor = Integer.parseInt(String.valueOf(v),2);
        if(cadena[0]=='1')
        {
            int signo = 1073741824;
            valor = valor|Integer.rotateLeft(signo, 1);
        }
        return valor;
    }
    //Funciones estandares
    private int Ch(int x,int y,int z)
    {
        return (x&y)^(~x&z);
    }
    private int Parity(int x,int y,int z)
    {
        return x ^ y ^ z;
    }
    private int Maj(int x,int y,int z)
    {
        return (x&y) ^ (x&z) ^(y&z);
    }
    private int f(int t,int x,int y,int z)
    {
        if(t<=19)
            return Ch(x,y,z);
        else if(t<=39)
            return Parity(x,y,z);
        else if(t<=59)
            return Maj(x,y,z);
        else 
            return Parity(x,y,z);
    }
    private int Rotar(int n,int c)
    {
        return Integer.rotateLeft(n, c);
    }
    //Definir constantes de algoritmo
    private int K(int t)
    {
        if(t<=19)
            return 0x5a827999;
        else if(t<=39)
            return 0x6ed9eba1;
        else if(t<=59)
            return 0x8f1bbcdc;
        else 
            return 0xca62c1d6;
    }
    private String SHA1(String cadena)
    {
        ArrayList<Integer> bloques = Bloques(cadena);
        ArrayList<Integer> H0 = new ArrayList<Integer>();
        ArrayList<Integer> H1 = new ArrayList<Integer>();
        ArrayList<Integer> H2 = new ArrayList<Integer>();
        ArrayList<Integer> H3 = new ArrayList<Integer>();
        ArrayList<Integer> H4 = new ArrayList<Integer>();
        H0.add(0x67452301);
        H1.add(0xefcdab89);
        H2.add(0x98badcfe);
        H3.add(0x10325476);
        H4.add(0xc3d2e1f0);
        ArrayList<Integer> grupo = new ArrayList<Integer>();
        for(int j=0;j<80;j++)
            grupo.add(0);
        for(int i=0; i<bloques.size()/16;i++)
        {
           for(int j=0;j<16;j++)
               grupo.set(j, bloques.get(j+16*i));
           for(int j=16;j<80;j++)
               grupo.set(j, Rotar(grupo.get(j-3)^grupo.get(j-8)^grupo.get(j-14)^grupo.get(j-16),1));
           int a = H0.get(H0.size()-1);
           int b = H1.get(H1.size()-1);
           int c = H2.get(H2.size()-1);
           int d = H3.get(H3.size()-1);
           int e = H4.get(H4.size()-1);
           for (int j=0; j<80;j++)
           {
               int t = (Rotar(a,5)+f(j,b,c,d)+e+K(j)+grupo.get(j))&0xffffffff;
               e = d;
               d = c;
               c = Rotar(b,30);
               b = a;
               a = t;
           }
           H0.add((a+H0.get(H0.size()-1))&0xffffffff);
           H1.add((b+H1.get(H1.size()-1))&0xffffffff);
           H2.add((c+H2.get(H2.size()-1))&0xffffffff);
           H3.add((d+H3.get(H3.size()-1))&0xffffffff);
           H4.add((e+H4.get(H4.size()-1))&0xffffffff);
        }
        int h0 = H0.get(H0.size()-1);
        int h1 = H1.get(H1.size()-1);
        int h2 = H2.get(H2.size()-1);
        int h3 = H3.get(H3.size()-1);
        int h4 = H4.get(H4.size()-1);
        return Integer.toHexString(h0)+Integer.toHexString(h1)+Integer.toHexString(h2)+Integer.toHexString(h3)+Integer.toHexString(h4);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSHA1;
    private javax.swing.JTextField txtCadena;
    private javax.swing.JTextField txtSHA1;
    // End of variables declaration//GEN-END:variables
}
