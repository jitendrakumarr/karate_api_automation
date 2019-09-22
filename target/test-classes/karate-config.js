function() {    
  var env = karate.env;
  if (!env) {
    env = 'stage';
  }
  var config = {
    any_variable_name : 'any_string_value_that_can_be_Accessed_in_feature_file'
  }
  return config;
}