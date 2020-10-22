LIBRARY ieee;
USE ieee.std_logic_1164.all;
USE ieee.std_logic_unsigned.all;
USE ieee.numeric_std.all;

ENTITY test_regfile IS
END test_regfile;

ARCHITECTURE test_b OF test_regfile IS
  CONSTANT dw : NATURAL := 4;
  CONSTANT size : NATURAL := 4;
  CONSTANT adrw : NATURAL := 2;
    
  COMPONENT regfile IS PORT (
    A : IN STD_LOGIC_VECTOR(dw-1 DOWNTO 0);
    Addr : IN STD_LOGIC_VECTOR(adrw-1 DOWNTO 0);
    we,clk : IN STD_LOGIC;
    C : OUT STD_LOGIC_VECTOR(dw-1 DOWNTO 0));
  END COMPONENT;
  
  SIGNAL A : STD_LOGIC_VECTOR(dw-1 DOWNTO 0);
  SIGNAL Addr : STD_LOGIC_VECTOR(adrw-1 DOWNTO 0);
  SIGNAL we,clk : STD_LOGIC;
  SIGNAL C : STD_LOGIC_VECTOR(dw-1 DOWNTO 0);
  
  BEGIN
    RF1: regfile PORT MAP(A=>A,Addr=>Addr,we=>we,clk=>clk,C=>C);
    PROCESS
      BEGIN
        --Write
        clk<='1';we<='1';WAIT FOR 50PS;
        A<="0101";Addr<="00";clk<='0';WAIT FOR 50PS;
        clk<='1';WAIT FOR 50PS;
        A<="1101";Addr<="01";clk<='0';WAIT FOR 50PS;
        clk<='1';WAIT FOR 50PS;
        A<="0010";Addr<="10";clk<='0';WAIT FOR 50PS;
        clk<='1';WAIT FOR 50PS;
        A<="1001";Addr<="11";clk<='0';WAIT FOR 50PS;
        --Read
        we<='0';
        Addr<="01";WAIT FOR 50PS;
        Addr<="11";WAIT FOR 50PS;
    END PROCESS;
END test_b;