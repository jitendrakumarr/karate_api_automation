@eCom @shoppingCartHal @hal
Feature: To verify shopping cart api

    Background:
        * def endpoints = read('file:'+endpoints_path+'endpoints.json')
        * def request_data =  request_data_path+'module_name'
        * def response_schema = response_schema_path+'module_name/'
        * def headers_data = read('file:'+request_headers_path+'module_name/headers.json')
        * headers headers_data
        * def Utils = Java.type('framework.Utils')
        And url base_url

    Scenario: Verify GET request schema and response code.
        Given path endpoints.modudleName1.pathName1
        Given param channels.id = 'auto_channel'
        Given param externalId = 'auto_externalId'
        And print headers_data
        When method GET
        Then assert responseStatus == response_data.success_code
        And def expectedSchema = read('file:'+response_schema+'/hal_get_schema.json')
        And match response contains expectedSchema

    Scenario: Verify POST request schema and response code.
        Given path endpoints.modudleName2.pathName2
        Given path endpoints.cartWriteController.postCreateCart
        Then def external_id = Utils.getRandomString(20)
        Given request read('file:'+request_data+'hal_post_cart_api.json')
        When method POST
        Then assert responseStatus == response_data.created_code
        Then def cart_id = $.id
        Then print cart_id
        Then def value = Utils.AddConfig('shopping_cart_id#'+cart_id)
        And def expectedSchema = read('file:'+response_schema+'/hal_post_schema.json')
        And match response contains expectedSchema

    Scenario: Verify PUT - Update request schema and response code.
        Given path endpoints.modudleName2.pathName2
        Given path endpoints.endpoints.cartWriteController.postCreateCart
        Given request read('file:'+request_data+'hal_put_cart_api.json')
        When method PUT
        Then assert responseStatus == response_data.created_code
        And def expectedSchema = read('file:'+response_schema+'/hal_put_schema.json')
        And match response contains expectedSchema