function() {
  var env = karate.env;

  if (!env) {
    env = 'stage';
  }
  var config = {
    base_url: karate.properties['base_url'],
    request_data_path: karate.properties['request_data_path'],
    endpoints_path: karate.properties['endpoints_path'],
    response_data_path: karate.properties['response_data_path'],
    response_schema_path:karate.properties['response_schema_path'],
    request_headers_path:karate.properties['request_headers_path'],
  }
  return config;
}