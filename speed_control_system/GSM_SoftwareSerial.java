#include <SoftwareSerial.h>

const int rxPin = 6;
const int txPin = 7;
const unsigned int gsmRxBufferLength = 600;

unsigned int i = 0;
char gsmRxBuffer[gsmRxBufferLength];

SoftwareSerial gsm(rxPin, txPin);

void clrRxBuffer(void)
{
    memset(gsmRxBuffer, 0, gsmRxBufferLength);      //清空
    i = 0;
}

void setup()
{
  gsm.begin(9600);
  Serial.begin(9600);
}

void loop() {
  gsm.println("AT+CSCS=\"GSM\"");
  delay(500);
  gsm.println("AT+CMGF=1");
  delay(500);
  gsm.println("AT+CMGS=\"15996236620\"");
  delay(500);
  gsm.println("hello");
  gsm.write(0x1A);
  /*gsm.println("Are you OK?");
  delay(500);
  gsm.print(char(26));
  delay(500);
  /*gsm.println("AT");
  delay(500);*/
  while (gsm.available()) {
    char c = gsm.read();
    Serial.print(c);
  }
}