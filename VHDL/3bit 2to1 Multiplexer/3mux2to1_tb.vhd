LIBRARY ieee;
USE ieee.std_logic_1164.ALL;

ENTITY test_3mux IS
END test_3mux;

ARCHITECTURE testb OF test_3mux IS
  COMPONENT triple_mux2to1 IS
  PORT(
    a, b: IN std_logic_vector(2 DOWNTO 0);
       s: IN std_logic;
       d: OUT std_logic_vector(2 DOWNTO 0));
  END COMPONENT triple_mux2to1;

  SIGNAL A, B : std_logic_vector(2 DOWNTO 0);
  SIGNAL S : std_logic;
  SIGNAL D : std_logic_vector(2 DOWNTO 0);

  BEGIN
    M1: triple_mux2to1 PORT MAP(a=>A, b=>B, s=>S, d=>D);
    PROCESS
      BEGIN
        A<="001";B<="010";S<='0'; WAIT FOR 50PS;
        A<="010";B<="100";S<='0'; WAIT FOR 50PS;
        A<="111";B<="011";S<='0'; WAIT FOR 50PS;
        A<="101";B<="111";S<='0'; WAIT FOR 50PS;
        
        A<="010";B<="001";S<='1'; WAIT FOR 50PS;
        A<="000";B<="101";S<='1'; WAIT FOR 50PS;
        A<="101";B<="010";S<='1'; WAIT FOR 50PS;
        A<="111";B<="101";S<='1'; WAIT FOR 50PS;
    END PROCESS;
END testb;