/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Exceptions.DbException;
import Exceptions.GeneralException;
import Model.ModelUsuario;
import View.ViewLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class ControllerUsuario implements MouseListener, KeyListener, ActionListener {

    ModelUsuario model;
    ViewLogin view;

    public ControllerUsuario(ModelUsuario model, ViewLogin view) {
        this.model = model;
        this.view = view;
        view.setModel(model);
        view.setController(this);
    }
    
    public boolean validarUsuario(String id, String contrasena) {
        try {
            return this.model.validarUsuario(id,contrasena);
        } catch (GeneralException ex) {
            Logger.getLogger(ControllerUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DbException ex) {
            Logger.getLogger(ControllerUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }
    public void show() {
        view.setVisible(true);
    }

    public void hide() {
        view.setVisible(false);
    }

}
