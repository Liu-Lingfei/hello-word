int analogInPin0 = A0;
int analogInPin1 = A1;
int analogOutPin = 9;
int sensorValue0 = 0;
int sensorValue1 = 0;
int Speed = 0;
int outputValue = 0;

void setup() {
  Serial.begin(9600);
}

void loop() {

  sensorValue0 = analogRead( analogInPin0);
  sensorValue1 = analogRead( analogInPin1);
  Speed = sensorValue0 - sensorValue1;
  outputValue = map( Speed,-1023,1023,140,10);
  
  analogWrite( analogOutPin, outputValue);

  Serial.print("\speed: ");
  Serial.print( Speed);
  Serial.print("\n");
  delay(2);
}