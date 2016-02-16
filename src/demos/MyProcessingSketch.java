package demos;

import processing.core.PApplet;
import processing.core.PImage;



public class MyProcessingSketch extends PApplet {

  public void setup() {
    size(1600,800);
    background(255);
  }

  
  public void draw() {
    stroke(0);
    if (mousePressed) {
      line(mouseX,mouseY,pmouseX,pmouseY);
    }
  }
}


