@module
Feature: To verify shopping cart api

    Background:
        * def endpoints = read('file:src/test/resources/endpoints/endpoints.json')
        * def request_data =  request_data_path+'module_name'
        * def response_schema = response_schema_path+'/module_name/'
        * def headers_data = read('file:'+request_headers_path+'module_name/headers.json')
        * headers headers_data
        And url base_url

    Scenario:
        Given path endpoints.modudleName1.pathName1
        # And print any_variable_name
        When method GET
        Then assert responseStatus == 200