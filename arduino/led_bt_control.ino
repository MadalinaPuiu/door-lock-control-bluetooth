#include <Servo.h>
Servo servo1;
int servoPin = 9;
int sensorPin = 3;
int ledPin = 8;

void setup() {
  Serial.begin(9600);
  pinMode(sensorPin, INPUT);
  pinMode(ledPin, OUTPUT);
  servo1.attach(servoPin);
  servo1.write(0);
}

void loop() {
  if (Serial.available() > 0)
  {
    // reading the data received from the bluetooth module
    char data = Serial.read();
    int valSensor = digitalRead(sensorPin);

    switch (data)
    {
      case 'a': {
          if (valSensor == HIGH) {
            digitalWrite(ledPin, HIGH);
            servo1.write(90);
            Serial.println("door is locked");
          } else {
            Serial.println("error");
          }
          break;
        } // when a is pressed on the app on your smart phone
      case 'd': {
          digitalWrite(ledPin, LOW);
          servo1.write(0);
          Serial.println("door is unlocked");
          break;
        }// when d is pressed on the app on your smart phone
      default : break;
    }
  }
  delay(50);
}
