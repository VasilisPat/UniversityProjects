LIBRARY ieee;
USE ieee.std_logic_1164.all;
USE ieee.std_logic_unsigned.all;
USE ieee.numeric_std.all;

ENTITY regfile IS
  GENERIC ( dw : natural := 4;
    size : natural := 4;
    adrw : natural := 2);
  PORT ( 
    A : IN STD_LOGIC_VECTOR(dw-1 DOWNTO 0);
    Addr : IN STD_LOGIC_VECTOR(adrw-1 DOWNTO 0);
    we : IN STD_LOGIC;
    clk : IN STD_LOGIC;
    C : OUT STD_LOGIC_VECTOR(dw-1 DOWNTO 0));
END regfile;

ARCHITECTURE behav OF regfile IS
  TYPE regArray IS ARRAY(0 TO size-1) OF STD_LOGIC_VECTOR(dw-1 DOWNTO 0);
  SIGNAL regfile : regArray;
  
  BEGIN
    PROCESS(clk)
    BEGIN
      IF (clk'EVENT AND clk='0') THEN
        IF we='1' THEN
          regfile(to_integer(unsigned(Addr))) <= A;
        END IF;
    END IF;
    C <= regfile(to_integer(unsigned(Addr)));
  END PROCESS;
END behav;