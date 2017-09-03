#include <SoftwareSerial.h>

const int analogInPin0 = A0;//VDR pins
const int analogInPin1 = A1;
const int rxpin = 3;//Bluetooth pins
const int txpin = 2;
const int analogOutPin = 9;//Output


int sensorValue0 = 0;//VDR values
int sensorValue1 = 0;
int Speed = 0;//Speed of the skateboard
int outputValue = 0;//Value of PWM

SoftwareSerial bluetooth(rxpin, txpin);//Initialize the Bluetooth pins

void setup() {
  Serial.begin(9600);//Initialize the COMs
  bluetooth.begin(9600);
}

void loop() {

  if (bluetooth.available()){//if the bluetooth is available, bluetooth is superior to VDRs
    int i = (int)bluetooth.read();

    Speed = map(i,0,100,-1024,1024);
    outputValue = map(i,0,100,140,10);

    Serial.print("bluetooth = ");
  } 

  else {//if the VDRs are not available
    sensorValue0 = analogRead( analogInPin0);
    sensorValue1 = analogRead( analogInPin1);

    Speed = sensorValue0 - sensorValue1;
    outputValue = map( Speed,-1023,1023,140,10);
  }

  analogWrite( analogOutPin, outputValue);//Output the PWM to output pin
  Serial.print("speed: ");
  Serial.print( Speed);
  /*Serial.print( "\toutput =");
  Serial.print( outputValue);*/
  Serial.print("\n");
  delay(2);
}