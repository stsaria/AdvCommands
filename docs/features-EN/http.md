# HTTP GET/POST
Send HTTP requests and receive responses.

## Usage
- GET request
```
httpget URL
```
- POST request
```
httppost argument_struct_variable_name URL
```

## Usage Examples
```
# Get IP using GET protocol
httpget https://ipinfo.io/ip
cmd say <r.content>

# User registration using POST protocol and output status code
setvar httpargs.name namae
httppost httpargs http://api.hoge.fuga/register
cmd say <r.statuscode>
```