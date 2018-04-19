# codingSystemSC

## General

A source-channel coding system for text files has been implemented. It takes a text file as input and uses two different variants of Lempel–Ziv as source coding: **LZW** and **LZMW**. The compressed text is sent on a channel with a single error probability (**Binary symmetric model**) or burst errors probabilities (**Gilbert–Elliott model**). It, then, goes through a channel coding; block codes have been used, in particular **Repetition code**, **Hamming code** and **Concatenated code**. At the receiver, decoding and decompression processes are performed to recover the data.

## Usage

For running **codingSystemSC** through the command line, type:

java -jar codingSystemSC.jar

The project comes with a GUI where the user can choose:

- **Input File**, browse in your file system to select a valid file. Some text files are provided in the folder *text*;

- **Source coding**, choose the lossless data compression algorithm to use;

- **Channel coding**, choose a channel coding using the following syntax:
	- rx where x is the length of the code (ex. r3) for *Repetition code*;
	- h(n,k) where k is the message length, and the block length is n=k+redundancy bits (ex. h(7,4)) for *Hamming codes*;
	- more of the two separated by whitespace (ex.  r3 r5 h(7,4) ) for *Concatenated codes*.

- **Channel model**, choose a channel model using the following syntax:
	- alpha crossover probability for a *Binary Symmetric model*;
	- Pgg Pbb Pg Pb for a *Gilbert–Elliott model*;

### Multiple tests 

Different settings can be compared in a 2D plot. 

- To compare different channel coding settings, enter the channels coding to compare (following the above syntax) separated by "**;**". For example *r3; r3 r3; r3 r5* compare a repetition code *r3* to two concatenated codes *r3 r3* and *r3 r5*.

- To compare different channel model settings, enter error probabilities (following the above syntax) separated by "**;**" :
	- In the case of *Binary Symmetric model* typing *0.001; 0.01; 0.1* the specified channel coding is performed with *alpha=0.001*,*alpha=0.01* and *alpha=0.1* and the results are plotted;
	- In the case of *Gilbert–Elliott model* the rules are the same but all probabilities have to be specified, for example *0.99 0.80 0.0001 0.01; 0.99 0.80 0.001 0.1*.
