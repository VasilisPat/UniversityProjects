LIBRARY ieee;
USE ieee.std_logic_1164.ALL;

ENTITY adder4 IS 
PORT(
  X, Y : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
  Cin : IN STD_LOGIC;
  Cout : OUT STD_LOGIC;
  S : OUT STD_LOGIC_VECTOR(3 DOWNTO 0));
END adder4;

ARCHITECTURE behav OF adder4 IS
  SIGNAL C3, C2, C1: STD_LOGIC;
BEGIN
  S(0)<=((X(0) XOR Y(0)) XOR Cin); 
  C1<=((X(0) AND Y(0)) OR (Y(0) AND Cin) OR (Cin AND X(0)));
  S(1)<=((X(1) XOR Y(1)) XOR C1); 
  C2<=((X(1) AND Y(1)) OR (Y(1) AND C1) OR (C1 AND X(1)));
  S(2)<=((X(2) XOR Y(2)) XOR C2); 
  C3<=((X(2) AND Y(2)) OR (Y(2) AND C2) OR (C2 AND X(2)));
  S(3)<=((X(3) XOR Y(3)) XOR C3); 
  Cout<=((X(3) AND Y(3)) OR (Y(3) AND C3) OR (C3 AND X(3))); 
END behav;