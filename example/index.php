<?php
$vcap_services = json_decode($_ENV['VCAP_SERVICES']);
$key = 'now';
$basic = 'Basic ' . base64_encode($vcap_services->{'p-map'}[0]->credentials->userId . ':' . $vcap_services->{'p-map'}[0]->credentials->password) . "\r\n";
$url = $vcap_services->{'p-map'}[0]->credentials->url;

if (isset($_GET['update'])) {
  $now = date( "Y/m/d/ H:i:s" );
  $opt = stream_context_create(array('http' => array(
    'method' => 'POST',
    'header'  => "Content-Type: text/plain\r\n" . "Authorization: " . $basic,
    'content' => $now
  )));
  file_get_contents($url . '/' . $key, false, $opt);
  echo $now;
} else {
  $opt = stream_context_create(array('http' => array(
    'method' => 'GET',
    'header'  => "Authorization: " . $basic
  )));
  echo file_get_contents($url . '/' . $key, false, $opt);
}
?>
