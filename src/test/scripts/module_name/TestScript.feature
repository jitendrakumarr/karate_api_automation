@module
Feature: To verify shopping cart api

    Background:
        * def endpoints = read('file:'+endpoints_path+'endpoints.json')
        * def request_data =  request_data_path+'module_name'
        * def response_schema = response_schema_path+'/module_name/'
        * def headers_data = read('file:'+request_headers_path+'headers.json')
        * headers headers_data.modudleName1
        And url base_url

    Scenario:
        Given path endpoints.modudleName1.pathName1
        # And print any_variable_name
        And print headers_data.modudleName1
        When method GET
        Then assert responseStatus == 200