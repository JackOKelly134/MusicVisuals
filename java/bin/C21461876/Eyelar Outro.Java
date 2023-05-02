package c21461876

import java.util.ArrayList;
import ddf.minim.;
import ie.tudublin.;
import processing. core.PApplet; 
import processing.core.PVector;



public class Eyelar Outro extends PApplet {

    Audio1 a1;
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    public Timer timer; 

    int mode = 0;
    float seconds = 0;
    float theta = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
    ArrayList<Star> stars = new ArrayList<Star>();
    float speed;

    PVector[][] globe;
    int r = 200;
    int total = 25;
    float angleX = 0;
    float angleY = 0;

    public Eyelar Outro(Audio1 al)
    {
        this.a1 = a1;
    }

    public void render()
    {
        // timer.update();
		float halfH = height / 2;
		float halfW = width / 2;
		float average = 0;
		float sum = 0;
		off += 1;
		// Calculate sum and average of the samples
		// Also lerp each element of buffer;
		for (int i = 0; i < ab.size(); i++) {
			sum += abs(ab.get(i));
		}
		average = sum / (float) ab.size();

		smoothedAmplitude = lerp(smoothedAmplitude, average, 0.2f);

		float cx = width / 2;
		float cy = height / 2;

		seconds = millis() / 1000.0f;
		System.out.println(seconds);

        if (seconds >= 50 && seconds >= 60){
			a1.background(0);
			a1.strokeWeight(2);
			a1.noFill();
			a1.beginShape();
			for (int i = 0; i < stars.size(); i++) {
				stars.get(i).update();
				stars.get(i).show();
			}
			for (float i = 0; i < TWO_PI; i += 0.01f) {
				a1.stroke(0);
				a1.strokeWeight(15);
				float r1 = width / 1.5f;
				float x2 = r1 * cos(i);
				float y1 = r1 * pow(sin(i), 3) * 0.2f;
				// puts the eye in the center of the screen
				a1.vertex(x2 + cx, y1 + halfH);

				a1.stroke(84, 143, 168);
				a1.strokeWeight(5);
				// make the ring go crazy and randomly but still on sync to music
				float r2 = width / 9 + (smoothedAmplitude * random(5, 100) * 2);
				float x = r2 * cos(i);
				// center the ring
				a1.vertex(x + cx, 50 + halfH);
			}
			a1.endShape(CLOSE);

			float r = map(smoothedAmplitude, 0, 0.5f, 100, 2000);
			a1.stroke(84, 143, 168);
			//circle(cx, cy, r + (smoothedAmplitude - 500));
			//circle(cx, cy, (r + 150) + (smoothedAmplitude - 500));
			//circle(cx, cy, (r - 150) + (smoothedAmplitude - 500));
		
		}
    }

    class Star {
        float x;
        float y;
        float z;
        float pz;

        Star() {
            x = random(-a1.width / 2, a1.width / 2);
            y = random(-a1.height / 2, a1.height / 2);
            z = random(a1.width / 2);
            pz = z;
        }

        void update() {
            z = z - speed;
            if (z < 1) {
                z = a1.width / 2;
                x = random(-a1.width / 2, a1.width / 2);
                y = random(-a1.height / 2, a1.height / 2);
                pz = z;
            }
        }

        void show() {
            fill(255);
            noStroke();

            float sx = map(x / z, 0, 1, 0, a1.width);
            float sy = map(y / z, 0, 1, 0, a1.height);

            float r = map(z, 0, a1.width / 2, 8, 0);
            ellipse(sx, sy, r, r);

            float px = map(x / pz, 0, 1, 0, a1.width);
            float py = map(y / pz, 0, 1, 0, a1.height);

            stroke(255);
            line(px, py, sx, sy);

            pz = z;
        }
    }


}
}