# HTTP GET/POST
Send HTTP requests and receive responses.

## Usage
- GET Request
```
httpget response_variable_name URL
```
- POST Request
```
httppost response_variable_name argument_struct_variable_name URL
```

## Examples
```
# Get IP using GET protocol
httpget ip https://ipinfo.io/ip
cmd say <ip>

# User registration using POST protocol and output status code
setvarG httpargs.name namae
httppost res httpargs http://api.hoge.fuga/register
cmd say <res.statuscode>
```