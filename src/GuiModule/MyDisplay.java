package GuiModule;

import processing.core.PApplet;

public class MyDisplay extends PApplet {

	public void setup() {
		size(400,400);
		background(23,52,240);
		
	}

	public void draw() {
		fill(0,0,7);
		ellipse(150,100,200,200);
		fill(255,255,0);
		ellipse(100,80,60,60);
		ellipse(200,80,60,60);
		arc(150,150,75,75,0,PI);
	}
}
