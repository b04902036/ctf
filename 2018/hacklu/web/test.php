<?php

require_once('flag.php');
error_reporting(0);


if(!isset($_GET['msg'])){
        highlight_file(__FILE__);
            die();

}

echo "Hello Hacker! Have a look around.\n";

@$k1=$_GET['key1'];
@$k2=$_GET['key2'];

$cc = 1337;$bb = 42;

if(intval($k1) !== $cc || $k1 === $cc){
        die("lol no11\n");

}

if(strlen($k2) == $bb){
    if(preg_match('/^\d+＄/', $k2) && !is_numeric($k2)){
        if($k2 == $cc){
                        @$cc = $_GET['cc'];
                                
        }
            
    }

}

list($k1,$k2) = [$k2, $k1];
$flag="flag{}";

if(substr($cc, $bb) === sha1($cc)){
    foreach ($_GET as $lel => $hack){
                $$lel = $hack;
                echo $lel;
                echo ' ';
                echo $hack;
                if($hack === '$flag'){
                    echo "success!";
                }    
    }

}
$‮b = "2";$a="‮b";//;1=b



if($$a !== $k1){
        die("lel no22\n");

}

// plz die now

assert_options(ASSERT_BAIL, 1);
echo $bb;
assert("2 < 1");
//echo $flag;
echo "Good Job ;)";
// // TODO
// // echo $flag;  
