package curva;

import java.awt.BasicStroke;//contruir un grosor para la linea
import java.awt.Color;
import java.awt.Graphics; //contexto de grafico basico (no permite dibujar el CubicCurve2D)
import java.awt.Graphics2D;//contexto grafico mas complejo, permite g2.draw(shape) en donde shape es representada por CubicCurve2D)
import java.awt.Point;// nos permite guardar coordenadas (x,y) (integers)
import java.awt.Rectangle; //representan nuestros puntos de control  cInicio, c1, c2, cFin, rectangulo;
import java.awt.event.MouseEvent; //eventos de mouse.
import java.awt.geom.CubicCurve2D; //nos permite dibujar un shape(forma) tipo CubicCurve2D
import java.awt.geom.Point2D;// nos permite guardar coordenadas (x,y) (floats) 
import java.util.ArrayList;//la clase que nos permitira guardar las figuras.
import javax.swing.JColorChooser;//selector de color 

/**
 *
 * @author flor
 */
public class Ventana extends javax.swing.JFrame {
    //arreglo contenedor de las figuras que se han dibujado
    ArrayList<Figuras> figuras = new <Figuras>ArrayList();
    //aqui se guarda temporalmente la figura actual, (la que se esta editando)
    CubicCurve2D c = new CubicCurve2D.Double();
    //contexto grafico que nos permite dibujar figuras mas complejas
    Graphics2D g2;
    //puntos de control,             
    Rectangle cInicio, c1, c2, cFin, rectangulo;
    ///coordenadas de la CubicCurve... 
    Point2D.Double inicio, fin, p1, p2, punto;
    
    //grosor del dibujo actual
    float grosor=1.0f;
    //puntos auxiliares para poder editar los puntos de control.
    int x, y;
    //variables de control, (se explica su funcionamiento mas tarde.)
    private boolean fueraRec = true;
    private boolean primeraVez = true;
    private boolean clicInicio = true;
    private boolean dibujando = false;
    private boolean editando = false;

    /**
     * Creates new form Ventana
     */
    public Ventana() {
        initComponents();
        
        //iniciamos nuestros objetos como nuevos puntos.
        inicio = new Point2D.Double();
        fin = new Point2D.Double();
        p1 = new Point2D.Double();
        p2 = new Point2D.Double();

        //iniciamos nuestros rectangulos (puntos de control)
        cInicio = new Rectangle(0, 0, 6, 6);
        c1 = new Rectangle(0, 0, 6, 6);
        c2 = new Rectangle(0, 0, 6, 6);
        cFin = new Rectangle(0, 0, 6, 6);
        
        this.setTitle(this.getWidth() + ":" + this.getHeight());//quitar esto de curvas sin comentarios

    }

    /*
    Actualiza los graficos, siempre es llamado cuando:
    se maximiza la ventana
    se dibuja una nueva linea,
    se edita un punto de control
    cambia el color de la linea
    cambie el grosor de la linea
    cuando se hace la llamada al metodo repaint();
    */
    public void paint(Graphics g) {
        super.paintComponents(g);
        update(g);//tambien se llama al metodo update cada vez que se llama a repaint()
    }

    /*
    Actualiza nuestros graficos:
    las figuras que fueron previamente hechas y la figura actual,(la que se esta editando.)
    */
    public void update(Graphics g) {
        
        //g2 es nuestro contexto grafico, (aqui guardamos la referencia que nos da el sistema operativo
        //ose Graphics g) g es solo de tipo Graphics, no permite dibujar cosas complejar. pero...
        //g2 si!.
        g2 = (Graphics2D) g;
        
        int w = this.getSize().width;//estas variables no son usada. se deben quitar
        int h = this.getSize().height;//quitar

        //aqui se usa la primer variable de control
        //esta variable de control permite ejecutar este codigo solo una vez
        //(la primera)
        if (primeraVez) {
            inicio.setLocation(-10, -10);
            fin.setLocation(-10, -10);
            p1.setLocation(-10, -10);
            p2.setLocation(-10, -10);

            cInicio.setLocation((int) ((inicio.x) - 3.0), (int) ((inicio.y) - 3));
            cFin.setLocation((int) ((fin.x) - 3), (int) ((fin.y) - 3));
            c1.setLocation((int) ((p1.x) - 3), (int) ((p1.y) - 3));
            c2.setLocation((int) ((p2.x) - 3), (int) ((p2.y) - 3));
            c.setCurve(inicio, p1, p2, fin);
            
            //desactivamos la variable para que esto ya no se vuelva a ejecutar.
            primeraVez = false;
        }
        //setStroke pone el grosor, que obtiene de la variable grosor.
        g2.setStroke(new BasicStroke(grosor));
        //ponemos el color del dibujo, del fondo que tiene nuestro jLabel1 actualmente, (que esta en el frame)
        g2.setPaint(jLabel1.getBackground());
        //y dibujamos, c y los puntos de control
        g2.draw(c);
        //para dibujar los puntos de control en verde.
        g2.setPaint(Color.green);
        g2.fill(cInicio);
        g2.fill(cFin);
        g2.fill(c1);
        g2.fill(c2);

        
        /*
        se dibujan todas las lineas dibujadas...
        */
        for (Figuras actual : figuras) {
            //este for, nos recorre todos los elementos del arreglo figuras
            //por cada elemento cambiamos las propiedades de g2 y dibujamos.
            g2.setColor(actual.color);
            g2.setStroke(new BasicStroke(actual.grosor));
            g2.draw(actual.c);

        }
        //regrsamos el color del contexto g2 al default (el de la etiqueta del jframe.)
        g2.setPaint(jLabel1.getBackground());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jTextField1.setText("1.0");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel2.setText("COLOR");

        jLabel3.setText("GROSOR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)))
                .addGap(0, 586, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(385, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        this.setTitle(evt.getX() + ":" + evt.getY());//esto tambien puede quitar, solo pone en el titulo, la coordenada donde se hizo clic
    }//GEN-LAST:event_formMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        
        /*
        EVENTO MUY IMPORTANTE MOUSEPRESSED =>          evt ^^^ es la variable que guarda la referencia del evento mouse pressed
        CUANDO SE DA CLIC DENTRO DEL JFRAME(ventana) SIEMPRE SE EJECUTA TODO ESTE CODIGO...
        A MENOS QUE.. EL ESTADO DE LAS VARIABLES DE CONTROL DICTEN LO CONTRARIO.
        */
        
        //este if nos detecta si hemos hecho doble clic
        if (evt.getClickCount() == 2) {
            //si se hace doble clic, significa que hemos terminado de editar un dibujo
            //por lo cual este dibujo de debe de guardar en el arreglo figuras.
            
            //cuando guardamos en el arreglo figuras creamos un nuevo objeto de la clase Figuras(que esta en el proyecto)
            //se mandan los argumentos que requiere el constructor de esta clase.
            figuras.add(new Figuras(c, g2,getGrosor()));
            //ponemos a la variable de control editando como false.
            //esto nos permitira hacer un nuevo dibujo.(no se puede hacer una nueva curva a menos que se de doble clic)
            editando = false;

        }

        //guardamos en x e y las coordenadas de donde se dio clic 
        x = evt.getX();
        y = evt.getY();

        
        //esto se ejecuta cuando la variable booleana, (variable de control) se encuentra en true
        //y no se esta editanto.... significa que no podemos poner una nueva curva
        // a menos que estas variables esten de la sig manera.
        //clicInicio = true;
        //editando = false;
        if (clicInicio && !editando) {
            
            //actualizamos todos los puntos de control de la curva en el punto donde se dio clic
            //todos los cuadritos verdes se localizan en donde se da clic y se empieza a dibujar.
            cInicio.setLocation(evt.getX() - 3, evt.getY() - 3);
            cFin.setLocation(evt.getX() - 3, evt.getY() - 3);
            c1.setLocation(evt.getX() - 3, evt.getY() - 3);
            c2.setLocation(evt.getX() - 3, evt.getY() - 3);

            //de modod que los cuadritos solo son referencias, debemos de 
            //modificar los puntos inicio,fin,p1,p2. que son los que realmente mueve a la curva. 
            inicio.setLocation(evt.getPoint());
            fin.setLocation(evt.getPoint());
            p1.setLocation(evt.getPoint());
            p2.setLocation(evt.getPoint());

            //movemos tantito a x e y. para que el cuadrito quede un poco fuera del puntero(se explica mas adelante en el evento mouserelease)
            x = cInicio.x - evt.getX();
            y = cInicio.y - evt.getY();
            
            //cuando dibujamos por primera vez se hace lo siguiente
            /*
            se da un clic(inicio), para terminar de dibujar la figura de debe de dar otro clic.
            
            como lo que vamos a actualizar mientras se esta trazando una nueva curva es el punto final, las variables
            auxliares rectangulo y punto se actualizan con la referencia del cuadrito verde cFin, y el punto fin de la curva c(curva auxiliar)
            */
            rectangulo = cFin;
            punto = fin;
            
            //variable de control que llama al metodo repaint() en el evento mousemove
            //(el proposito de tener esta variable en true se explica en el evento mousemove)
            dibujando = true;
         
            //si ya hemos dado el primer clic, desactivamos esta variable, para ya no ejecutar este codigo.
            clicInicio = false;
            actualizarFigura(evt);//este metodo llama a repaint(); (se explica mas abajo)
        } else {//este codigo no se ejecuta en el primer clic, debido a que  entramos al if y no al else
            //pero cuando se desactiva la variable booleana de control clicInicio = false. causa que ya no entremos al if
            //y ejecutemos el else
            //llegando al else,
            if (dibujando) {// solo ejecutamos este codigo si estamos en estado dibujando. 
                /*
                si estamos dibujando significa que estamos a punto de dar el clic numero 2. 
                el clic 1 fue cuando empezamos a dibujar 
                el clic 2 es cuando hemos decicido donde poner el segundo punto de nuestra curva.
                
                actualizamos entonces el cuadrito verde cFin.
                */
                cFin.x = evt.getX() - 3;
                cFin.y = evt.getY() - 3;
                
                //variables auxiliares que mueven tantito el cuadrito hacia arriba
                x = cFin.x - evt.getX();
                y = cFin.y - evt.getY();
                
                
                rectangulo = cFin;
                punto = fin;

                //entramos a modo editando, que nos pone a los cuadritos verdes en la curva para poder editarlos
                //para ello ponemos a editando en true;
                editando = true;
                //desactivamos a dibujando..(se explica en el evento mousemove)
                dibujando = false;
                //significa que una vez trazada nuestra figura, ya podemo dibujar otra.
                //para ello ponemos a clicInicio en true.
                clicInicio = true;
               
                actualizarFigura(evt);//actualizamos el grafico con la llamada a repaint();
            } else if (editando) {
                /*
                ya hemos hecho el clic1 + clic 2. (la figura ya esta dibujada con puntos incio y fin y tambien sus puntos de control)
                //(cuadritos verdes)
                
                //ESTOS IF ELSE NOS VERIFICAN SI HEMOS DADO CLIC EN UNO DE LOS PUNTOS DE CONTROL
                (CUADRITOS VERDES)
                SI DETECTAN EN CUAL PUNTO SE DIO CLIC Y UTILIZA LAS VARIABLES AUXILIARES QUE NOS PERMITEN
                MOVER ESE PUNTO DE CONTROL EN ESPECIFICO.
                */
                if (cInicio.contains(x, y)) {//SI SE DIO CLIC EN EL CUADRITO VERDE DE INICIO
                    /*
                    actualizamos el punto que se va a mover en este caso cInicio
                    en rectangulo(variable auxiliar) y tambien a punto = inicio.
                    y llamamos a repaint. se hace en los 3 if restantes con su respectivo cuadrito.
                    */
                    rectangulo = cInicio;
                    punto = inicio;
                    x = cInicio.x - evt.getX();
                    y = cInicio.y - evt.getY();
                    actualizarFigura(evt);
                } else if (cFin.contains(x, y)) {//SI SE DIO CLIC EN EL CUADRITO VERDE DE FIN
                    rectangulo = cFin;
                    punto = fin;
                    x = cFin.x - evt.getX();
                    y = cFin.y - evt.getY();
                    actualizarFigura(evt);
                } else if (c1.contains(x, y)) {//SI SE DIO CLIC EN EL CUADRITO VERDE PUNTO DE CONTROL 1
                    rectangulo = c1;
                    punto = p1;
                    x = c1.x - evt.getX();
                    y = c1.y - evt.getY();
                    actualizarFigura(evt);
                } else if (c2.contains(x, y)) {//SI SE DIO CLIC EN EL CUADRITO VERDE PUNTO DE CONTROL 2
                    rectangulo = c2;
                    punto = p2;
                    x = c2.x - evt.getX();
                    y = c2.y - evt.getY();
                    actualizarFigura(evt);
                } else {//si estamos en modo editando y se a dado un clic, pero este clic no se ha dado en uno de los 
                    //cuadritos verdes, pues simplemente ponemos esta variable en true, se explica tambien en el evento mouseDragged
                    fueraRec = true;
                }

            }
        }


    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
/*
        Se ejecuta este codigo solo cuando se ha dado clic en uno de lo cuadritos verdes. para poder simular que se mueve este punto
        
        */
        if (!fueraRec) {
            //llamamos al metodo repaint. que es lo que nos simula el movimiento, ya que actualiza 
            //las variable auxilares rectangulo y punto.
            /*
            Lo que hacemos es damos clic en un cuadrito verde y este actualiza nuestras variables auxiliares rectangulo y punto
            al momento de arrastrar, en objeto evt, que se recibie como argumento en este metodo va guardando
            la coordenada de a donde se esta arrastrando este cuadrito verde. 
            este metodo actualiza ese cuadrito verde y el punto. de la curva auxiliar c.
            esto hace que se vea el movimiento en tiempo real.
            */
            actualizarFigura(evt);
        }
        this.setTitle(evt.getX() + ":" + evt.getY());

    }//GEN-LAST:event_formMouseDragged

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
/*
        
        El evento MouseReleased ocurre cuando de libera el clic de mouse.
        
        lo que hacemos es si liberamos el clic mientras estamos sobre un cuadrito verde, lo movemos tantito
        y actualizmos la figura.
        */
        if (cInicio.contains(evt.getX(), evt.getY())) {
            rectangulo = cInicio;
            punto = inicio;
            actualizarFigura(evt);
        } else if (cFin.contains(evt.getX(), evt.getY())) {
            rectangulo = cFin;
            punto = fin;
            actualizarFigura(evt);
        } else if (c1.contains(evt.getX(), evt.getY())) {
            rectangulo = c1;
            punto = p1;
            actualizarFigura(evt);
        } else if (c2.contains(evt.getX(), evt.getY())) {
            rectangulo = c2;
            punto = p2;
            actualizarFigura(evt);
        } else {//si se ha liberado el clic de mouse fuera de uno de los cuadritos verdes. ponemos a 
            //fueraRect en false. para que cuando evitar actualizar la figura cuando se arrastre el mouse  sin dar clic en unos de los 
            //cuadritos verdes.
            fueraRec = false;
        }

    }//GEN-LAST:event_formMouseReleased

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
        
        //este evento da la sensacion de estar dibujando la figura en tiempo real.
        
        /*
        siempre y cuando estemos en estado dibujando. es decir:
        hemos dado el clic 1 (inicio ) 
        pero no el clic 2. ( no hemos terminado de poner el punto final de la curva.)
        */
        if (dibujando) {
            
            /*
            este pedazo de codigo lo que nos haces es actualizar los puntos de control c1 y c2
            y los puntos de la curva p1 y p1
            
            hacemo el calculo para que esos puntos queden exactamente a la mismadistancia y dividir la recta en partes iguales
            basicamente hacemos que los puntos inicio, p1, p2,fin dividan a la curva en partes iguales
            
            se utiliza el metodo matematico de geometria analitica llama, division de un segmento dada una razon.
            en donde r es la razon = numero de partes en la que la curva se divide.
            r = 1.0 / 2.0;    => 1.0 + 2.0   = 3
            si vemos nuestra curva cuando la terminamos de dibujar queda dividido en tres partes iguales.
            
            este metodo hace esa magia bonita. :D
            
            
            */
            double r = 1.0 / 2.0;
            double cx = (((inicio.x + r * fin.x)) / (1.0 + r));
            double cy = ((inicio.y + r * fin.y) / (1.0 + r));
            p1.setLocation(cx, cy);//coordenadas para el primer punto de control
            cx = ((fin.x + inicio.x * r) / (1 + r));
            cy = ((fin.y + inicio.y * r) / (1 + r));
            p2.setLocation(cx, cy);//coordenadas para el segundo punto de control
            /*
            una vez teniendo los puntos de control de la curva actualizamos los cuadritos verdes c1 y c1 en esos puntos.
            */
            c1.setLocation((int) p1.x, (int) p1.y);
            c2.setLocation((int) p2.x, (int) p2.y);
            
            //ponemos a fin siempre en donde se ecuentra el mouse actualmente. (osea donde se ha movido el mouse)
            cFin.x = evt.getX() - 3;
            cFin.y = evt.getY() - 3;

            //actualizamos las variables auxiliares rectangulo y punto
            rectangulo = cFin;
            punto = fin;
            actualizarFigura(evt);//redibujamos.
        }

    }//GEN-LAST:event_formMouseMoved

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_formKeyTyped

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        
        //el evento mousecliced sobre nuestro jLabel1, (rectangulo para color)
        //llamamos a Jcolorchoose y al metodo show diaglog para que nos muestre la paleta de colores y el usuario pueda elegir una.
        Color c = JColorChooser.showDialog(null, "Choose a Color", jLabel1.getBackground());
        if (c != null) {//entramos aqui  solo cuando el usuario haya elegido un color
            jLabel1.setBackground(c);//actualizamos el fondo del jlabel(color)
            g2.setPaint(c);//y ponemos el contexto en ese color, (esto causa que las curvas que se dibujen enseguida ya tomen el nuevo color)
            repaint();//repintamos
        }


    }//GEN-LAST:event_jLabel1MouseClicked

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:+
  
        
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
  /*
        este evento detecta cuando una tecla del teclado se ha libreado keyReleased (se esta escribiendo dentro del jtextfield1 = grosor de la figura)
        
        
        */
        
        
        char c = evt.getKeyChar(); // Get the typed character  evt
  //es un filtro que nos permite insertar dentro de nuestro jtextfield solo numeros y puntos. no letras
    if (c != evt.VK_BACK_SPACE && c != evt.VK_DELETE) {  
      
      if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9'||c=='.')) {  
        evt.consume();  
      }  
    }
    //llamamos al metodo que actualiza la variable grosor.
    setGrosor();
        
    }//GEN-LAST:event_jTextField1KeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {//este es el metodo que se ejecuta cuando corremos nuestra aplicacion
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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);//llamamos al constructor de la clase Ventana y la ponemos como visible
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    private void actualizarFigura(MouseEvent evt) {

        //actualiza figura, pone al cuadrito verde rectangulo a donde la variable recibida como parametro le diga.
        //este metodo es muy llamado cuando se requiere redibujar la figura en cierto lugar.
        rectangulo.setLocation((x + evt.getX()) - 3, (y + evt.getY()) - 3);
        punto.setLocation(x + evt.getX(), y + evt.getY());

        //metodo que nos detecta si nos estamos saliendo del jframe.
        verificaFrame();
        //ponemos a la curva auxiliar c en las coordenadas inicio,p1,p2,fin
        c.setCurve(inicio, p1, p2, fin);
        //llamamos a repaint() que a su vez llama al metodo paint y este llama a update, explicados arriba.
        repaint();
    }

    private void verificaFrame() {

    
        int new_x = rectangulo.x;
        int new_y = rectangulo.y;

        double new_px = punto.x;
        double new_py = punto.y;

        //si nos estamos saliendo de la longitud del frame, pues ponemos a los puntos de control 
        //a las orillitas de este, evitamos que estos se pierdan fuera del frame.

        if ((rectangulo.x + rectangulo.width) > this.getWidth()) {
            new_x = (int) this.getWidth() - (rectangulo.width + 8);
        }
        if ((rectangulo.y + rectangulo.width) > this.getHeight()) {
            new_y = (int) this.getHeight() - (rectangulo.height + 8);
        }
        if (rectangulo.y < 0) {
            new_y = 8;
        }
        if (rectangulo.x < 0) {
            new_x = 8;
        }

        if (punto.x > this.getWidth()) {
            new_px = (int) this.getWidth() - 5;
        }
        if (punto.y > this.getHeight()) {
            new_py = (int) this.getHeight() - 5;
        }
        if (punto.x < 0) {
            new_px = 10;
        }
        if (punto.y < 0) {
            new_py = 10;
        }

        rectangulo.setLocation(new_x, new_y);
        punto.setLocation(new_px, new_py);

    }

    private void setGrosor() {

       try{
//ponemos el grosor. 
           grosor = Float.parseFloat(jTextField1.getText());
           repaint();
       }catch(NumberFormatException ex){
           
           //capturamos excepciones por si ocurre algo que no pueda transformar el numero del jtextField a flotante.
       }
   
    }
    private float getGrosor(){
        return grosor;
    }

}
