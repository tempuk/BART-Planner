<?php
$apiKey = "MDDM-R6Y8-5YA6-VDMW";
define('CACHE_DIR', realpath('/tmp'));


$includeNodes = array("name", "abbr", "routeID", "number", "color");


$doc = new DOMDOcument();
$doc->load('/Users/uzik/routes.xml');
$xpath = new DOMXPath($doc);
$nodes = $xpath->query("//route");
for ( $i=0; $i<$nodes->length; $i++ ) {
	$node = $nodes->item($i);
	$data = array();
	foreach ($includeNodes as $includeNode) {
		$data[$includeNode] = $xpath->query($includeNode, $node)->item(0)->nodeValue;
	}
	
	print implode("\t", $data) . "\n";
}

?>