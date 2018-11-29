 <?php

highlight_file(__FILE__);

//$waf = Array("'", "\"", "^", "|", "l", "p");
$waf = Array("j");
$_ = $_GET['a'];
if(isset($_)) {
    //sort($_);
    foreach ($_ as $jason)
        echo $jason;
    $s = '';
    for($i = 0; $i < count($_); $i++)
        $s .= chr($_[$i]);
    if(preg_match("/[\w]{5,}/is", $s))
        die("GG");

    for($i = 0; $i < count($waf); $i++)
        if(strpos($s, $waf[$i]) !== FALSE)
            die("GG");
    
    echo '\n';
    echo "success";
    echo '\n';
    echo $s;
    eval(substr($s, 0, 25));
}

