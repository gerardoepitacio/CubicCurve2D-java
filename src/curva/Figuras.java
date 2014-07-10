/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package curva;

import java.awt.Color;//color a la curva
import java.awt.Graphics2D;//contexto que nos permite dibujar la CubicCurve2D
import java.awt.geom.CubicCurve2D;

/**
 *  Esta clase guarda la referencia de cada dibujo que se hace en el jframe
 *  para despues poder ser repintada cada se se llame al metodo repaint();
 * @author flor
 */
public class Figuras {
    
    CubicCurve2D c;
    float grosor;
    Color color;
    
    Figuras(CubicCurve2D figura, Graphics2D contexto,float g){
        c = (CubicCurve2D)figura.clone();
        this.grosor = g;
        color = contexto.getColor();
    }
    
}
