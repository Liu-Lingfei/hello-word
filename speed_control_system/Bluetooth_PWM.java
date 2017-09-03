#include <SoftwareSerial.h>

const int rxpin = 2;
const int txpin = 3;
const int analogOutPin = 9;

int outputValue = 0;
int Speed = 0;

SoftwareSerial bluetooth(rxpin, txpin);

void setup() {
  Serial.begin(9600);
  bluetooth.begin(9600);
  Serial.println("Serial ready");
  bluetooth.println("bluetooth ready");
}

void loop() {
  if(bluetooth.available()){
    int i = (int)bluetooth.read();
    Speed = map(i,0,100,-1024,1024);
    outputValue = map(i,0,100,140,10);
    
    analogWrite(analogOutPin, outputValue);
    
    Serial.print("bluetooth = ");
    Serial.print(i);
    Serial.print("\tspeed = ");
    Serial.print(Speed);
    Serial.print("\n");
  } 
}