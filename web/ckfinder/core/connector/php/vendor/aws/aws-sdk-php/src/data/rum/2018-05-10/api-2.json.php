<?php
// This file was auto-generated from sdk-root/src/data/rum/2018-05-10/api-2.json
return [ 'version' => '2.0', 'metadata' => [ 'apiVersion' => '2018-05-10', 'endpointPrefix' => 'rum', 'jsonVersion' => '1.1', 'protocol' => 'rest-json', 'serviceFullName' => 'CloudWatch RUM', 'serviceId' => 'RUM', 'signatureVersion' => 'v4', 'signingName' => 'rum', 'uid' => 'rum-2018-05-10', ], 'operations' => [ 'CreateAppMonitor' => [ 'name' => 'CreateAppMonitor', 'http' => [ 'method' => 'POST', 'requestUri' => '/appmonitor', 'responseCode' => 200, ], 'input' => [ 'shape' => 'CreateAppMonitorRequest', ], 'output' => [ 'shape' => 'CreateAppMonitorResponse', ], 'errors' => [ [ 'shape' => 'ConflictException', ], [ 'shape' => 'ServiceQuotaExceededException', ], [ 'shape' => 'InternalServerException', ], [ 'shape' => 'ValidationException', ], [ 'shape' => 'ThrottlingException', ], [ 'shape' => 'AccessDeniedException', ], ], 'idempotent' => true, ], 'DeleteAppMonitor' => [ 'name' => 'DeleteAppMonitor', 'http' => [ 'method' => 'DELETE', 'requestUri' => '/appmonitor/{Name}', 'responseCode' => 200, ], 'input' => [ 'shape' => 'DeleteAppMonitorRequest', ], 'output' => [ 'shape' => 'DeleteAppMonitorResponse', ], 'errors' => [ [ 'shape' => 'ConflictException', ], [ 'shape' => 'ResourceNotFoundException', ], [ 'shape' => 'InternalServerException', ], [ 'shape' => 'ValidationException', ], [ 'shape' => 'ThrottlingException', ], [ 'shape' => 'AccessDeniedException', ], ], 'idempotent' => true, ], 'GetAppMonitor' => [ 'name' => 'GetAppMonitor', 'http' => [ 'method' => 'GET', 'requestUri' => '/appmonitor/{Name}', 'responseCode' => 200, ], 'input' => [ 'shape' => 'GetAppMonitorRequest', ], 'output' => [ 'shape' => 'GetAppMonitorResponse', ], 'errors' => [ [ 'shape' => 'ResourceNotFoundException', ], [ 'shape' => 'InternalServerException', ], [ 'shape' => 'ValidationException', ], [ 'shape' => 'ThrottlingException', ], [ 'shape' => 'AccessDeniedException', ], ], ], 'GetAppMonitorData' => [ 'name' => 'GetAppMonitorData', 'http' => [ 'method' => 'POST', 'requestUri' => '/appmonitor/{Name}/data', 'responseCode' => 200, ], 'input' => [ 'shape' => 'GetAppMonitorDataRequest', ], 'output' => [ 'shape' => 'GetAppMonitorDataResponse', ], 'errors' => [ [ 'shape' => 'ResourceNotFoundException', ], [ 'shape' => 'InternalServerException', ], [ 'shape' => 'ValidationException', ], [ 'shape' => 'ThrottlingException', ], [ 'shape' => 'AccessDeniedException', ], ], ], 'ListAppMonitors' => [ 'name' => 'ListAppMonitors', 'http' => [ 'method' => 'POST', 'requestUri' => '/appmonitors', 'responseCode' => 200, ], 'input' => [ 'shape' => 'ListAppMonitorsRequest', ], 'output' => [ 'shape' => 'ListAppMonitorsResponse', ], 'errors' => [ [ 'shape' => 'InternalServerException', ], [ 'shape' => 'ValidationException', ], [ 'shape' => 'ThrottlingException', ], [ 'shape' => 'AccessDeniedException', ], ], ], 'ListTagsForResource' => [ 'name' => 'ListTagsForResource', 'http' => [ 'method' => 'GET', 'requestUri' => '/tags/{ResourceArn}', 'responseCode' => 200, ], 'input' => [ 'shape' => 'ListTagsForResourceRequest', ], 'output' => [ 'shape' => 'ListTagsForResourceResponse', ], 'errors' => [ [ 'shape' => 'ResourceNotFoundException', ], [ 'shape' => 'InternalServerException', ], [ 'shape' => 'ValidationException', ], ], ], 'PutRumEvents' => [ 'name' => 'PutRumEvents', 'http' => [ 'method' => 'POST', 'requestUri' => '/appmonitors/{Id}/', 'responseCode' => 200, ], 'input' => [ 'shape' => 'PutRumEventsRequest', ], 'output' => [ 'shape' => 'PutRumEventsResponse', ], 'errors' => [ [ 'shape' => 'ResourceNotFoundException', ], [ 'shape' => 'InternalServerException', ], [ 'shape' => 'ValidationException', ], [ 'shape' => 'ThrottlingException', ], [ 'shape' => 'AccessDeniedException', ], ], 'endpoint' => [ 'hostPrefix' => 'dataplane.', ], ], 'TagResource' => [ 'name' => 'TagResource', 'http' => [ 'method' => 'POST', 'requestUri' => '/tags/{ResourceArn}', 'responseCode' => 200, ], 'input' => [ 'shape' => 'TagResourceRequest', ], 'output' => [ 'shape' => 'TagResourceResponse', ], 'errors' => [ [ 'shape' => 'ResourceNotFoundException', ], [ 'shape' => 'InternalServerException', ], [ 'shape' => 'ValidationException', ], ], 'idempotent' => true, ], 'UntagResource' => [ 'name' => 'UntagResource', 'http' => [ 'method' => 'DELETE', 'requestUri' => '/tags/{ResourceArn}', 'responseCode' => 200, ], 'input' => [ 'shape' => 'UntagResourceRequest', ], 'output' => [ 'shape' => 'UntagResourceResponse', ], 'errors' => [ [ 'shape' => 'ResourceNotFoundException', ], [ 'shape' => 'InternalServerException', ], [ 'shape' => 'ValidationException', ], ], 'idempotent' => true, ], 'UpdateAppMonitor' => [ 'name' => 'UpdateAppMonitor', 'http' => [ 'method' => 'PATCH', 'requestUri' => '/appmonitor/{Name}', 'responseCode' => 200, ], 'input' => [ 'shape' => 'UpdateAppMonitorRequest', ], 'output' => [ 'shape' => 'UpdateAppMonitorResponse', ], 'errors' => [ [ 'shape' => 'ConflictException', ], [ 'shape' => 'ResourceNotFoundException', ], [ 'shape' => 'InternalServerException', ], [ 'shape' => 'ValidationException', ], [ 'shape' => 'ThrottlingException', ], [ 'shape' => 'AccessDeniedException', ], ], ], ], 'shapes' => [ 'AccessDeniedException' => [ 'type' => 'structure', 'required' => [ 'message', ], 'members' => [ 'message' => [ 'shape' => 'String', ], ], 'error' => [ 'httpStatusCode' => 403, 'senderFault' => true, ], 'exception' => true, ], 'AppMonitor' => [ 'type' => 'structure', 'members' => [ 'AppMonitorConfiguration' => [ 'shape' => 'AppMonitorConfiguration', ], 'Created' => [ 'shape' => 'ISOTimestampString', ], 'DataStorage' => [ 'shape' => 'DataStorage', ], 'Domain' => [ 'shape' => 'AppMonitorDomain', ], 'Id' => [ 'shape' => 'AppMonitorId', ], 'LastModified' => [ 'shape' => 'ISOTimestampString', ], 'Name' => [ 'shape' => 'AppMonitorName', ], 'State' => [ 'shape' => 'StateEnum', ], 'Tags' => [ 'shape' => 'TagMap', ], ], ], 'AppMonitorConfiguration' => [ 'type' => 'structure', 'members' => [ 'AllowCookies' => [ 'shape' => 'Boolean', ], 'EnableXRay' => [ 'shape' => 'Boolean', ], 'ExcludedPages' => [ 'shape' => 'Pages', ], 'FavoritePages' => [ 'shape' => 'FavoritePages', ], 'GuestRoleArn' => [ 'shape' => 'Arn', ], 'IdentityPoolId' => [ 'shape' => 'IdentityPoolId', ], 'IncludedPages' => [ 'shape' => 'Pages', ], 'SessionSampleRate' => [ 'shape' => 'SessionSampleRate', ], 'Telemetries' => [ 'shape' => 'Telemetries', ], ], ], 'AppMonitorDetails' => [ 'type' => 'structure', 'members' => [ 'id' => [ 'shape' => 'String', ], 'name' => [ 'shape' => 'String', ], 'version' => [ 'shape' => 'String', ], ], ], 'AppMonitorDomain' => [ 'type' => 'string', 'max' => 253, 'min' => 1, 'pattern' => '^(localhost)|^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$|^(?![-.])([A-Za-z0-9-\\.\\-]{0,63})((?![-])([a-zA-Z0-9]{1}|^[a-zA-Z0-9]{0,1}))\\.(?![-])[A-Za-z-0-9]{1,63}((?![-])([a-zA-Z0-9]{1}|^[a-zA-Z0-9]{0,1}))|^(\\*\\.)(?![-.])([A-Za-z0-9-\\.\\-]{0,63})((?![-])([a-zA-Z0-9]{1}|^[a-zA-Z0-9]{0,1}))\\.(?![-])[A-Za-z-0-9]{1,63}((?![-])([a-zA-Z0-9]{1}|^[a-zA-Z0-9]{0,1}))', ], 'AppMonitorId' => [ 'type' => 'string', 'max' => 36, 'min' => 36, 'pattern' => '^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$', ], 'AppMonitorName' => [ 'type' => 'string', 'max' => 255, 'min' => 1, 'pattern' => '^(?!\\.)[\\.\\-_#A-Za-z0-9]+$', ], 'AppMonitorSummary' => [ 'type' => 'structure', 'members' => [ 'Created' => [ 'shape' => 'ISOTimestampString', ], 'Id' => [ 'shape' => 'AppMonitorId', ], 'LastModified' => [ 'shape' => 'ISOTimestampString', ], 'Name' => [ 'shape' => 'AppMonitorName', ], 'State' => [ 'shape' => 'StateEnum', ], ], ], 'AppMonitorSummaryList' => [ 'type' => 'list', 'member' => [ 'shape' => 'AppMonitorSummary', ], ], 'Arn' => [ 'type' => 'string', 'pattern' => 'arn:[^:]*:[^:]*:[^:]*:[^:]*:.*', ], 'Boolean' => [ 'type' => 'boolean', 'box' => true, ], 'ConflictException' => [ 'type' => 'structure', 'required' => [ 'message', 'resourceName', ], 'members' => [ 'message' => [ 'shape' => 'String', ], 'resourceName' => [ 'shape' => 'String', ], 'resourceType' => [ 'shape' => 'String', ], ], 'error' => [ 'httpStatusCode' => 409, 'senderFault' => true, ], 'exception' => true, ], 'CreateAppMonitorRequest' => [ 'type' => 'structure', 'required' => [ 'Domain', 'Name', ], 'members' => [ 'AppMonitorConfiguration' => [ 'shape' => 'AppMonitorConfiguration', ], 'CwLogEnabled' => [ 'shape' => 'Boolean', ], 'Domain' => [ 'shape' => 'AppMonitorDomain', ], 'Name' => [ 'shape' => 'AppMonitorName', ], 'Tags' => [ 'shape' => 'TagMap', ], ], ], 'CreateAppMonitorResponse' => [ 'type' => 'structure', 'members' => [ 'Id' => [ 'shape' => 'AppMonitorId', ], ], ], 'CwLog' => [ 'type' => 'structure', 'members' => [ 'CwLogEnabled' => [ 'shape' => 'Boolean', ], 'CwLogGroup' => [ 'shape' => 'String', ], ], ], 'DataStorage' => [ 'type' => 'structure', 'members' => [ 'CwLog' => [ 'shape' => 'CwLog', ], ], ], 'DeleteAppMonitorRequest' => [ 'type' => 'structure', 'required' => [ 'Name', ], 'members' => [ 'Name' => [ 'shape' => 'AppMonitorName', 'location' => 'uri', 'locationName' => 'Name', ], ], ], 'DeleteAppMonitorResponse' => [ 'type' => 'structure', 'members' => [], ], 'EventData' => [ 'type' => 'string', ], 'EventDataList' => [ 'type' => 'list', 'member' => [ 'shape' => 'EventData', ], ], 'FavoritePages' => [ 'type' => 'list', 'member' => [ 'shape' => 'String', ], 'max' => 50, 'min' => 0, ], 'GetAppMonitorDataRequest' => [ 'type' => 'structure', 'required' => [ 'Name', 'TimeRange', ], 'members' => [ 'Filters' => [ 'shape' => 'QueryFilters', ], 'MaxResults' => [ 'shape' => 'MaxQueryResults', ], 'Name' => [ 'shape' => 'AppMonitorName', 'location' => 'uri', 'locationName' => 'Name', ], 'NextToken' => [ 'shape' => 'Token', ], 'TimeRange' => [ 'shape' => 'TimeRange', ], ], ], 'GetAppMonitorDataResponse' => [ 'type' => 'structure', 'members' => [ 'Events' => [ 'shape' => 'EventDataList', ], 'NextToken' => [ 'shape' => 'Token', ], ], ], 'GetAppMonitorRequest' => [ 'type' => 'structure', 'required' => [ 'Name', ], 'members' => [ 'Name' => [ 'shape' => 'AppMonitorName', 'location' => 'uri', 'locationName' => 'Name', ], ], ], 'GetAppMonitorResponse' => [ 'type' => 'structure', 'members' => [ 'AppMonitor' => [ 'shape' => 'AppMonitor', ], ], ], 'ISOTimestampString' => [ 'type' => 'string', 'max' => 19, 'min' => 19, 'pattern' => '/d{4}-[01]/d-[0-3]/dT[0-2]/d:[0-5]/d:[0-5]/d/./d+([+-][0-2]/d:[0-5]/d|Z)', ], 'IdentityPoolId' => [ 'type' => 'string', 'max' => 55, 'min' => 1, 'pattern' => '[\\w-]+:[0-9a-f-]+', ], 'Integer' => [ 'type' => 'integer', 'box' => true, ], 'InternalServerException' => [ 'type' => 'structure', 'required' => [ 'message', ], 'members' => [ 'message' => [ 'shape' => 'String', ], 'retryAfterSeconds' => [ 'shape' => 'Integer', 'location' => 'header', 'locationName' => 'Retry-After', ], ], 'error' => [ 'httpStatusCode' => 500, ], 'exception' => true, 'fault' => true, 'retryable' => [ 'throttling' => false, ], ], 'JsonValue' => [ 'type' => 'string', ], 'ListAppMonitorsRequest' => [ 'type' => 'structure', 'members' => [ 'MaxResults' => [ 'shape' => 'Integer', 'location' => 'querystring', 'locationName' => 'maxResults', ], 'NextToken' => [ 'shape' => 'String', 'location' => 'querystring', 'locationName' => 'nextToken', ], ], ], 'ListAppMonitorsResponse' => [ 'type' => 'structure', 'members' => [ 'AppMonitorSummaries' => [ 'shape' => 'AppMonitorSummaryList', ], 'NextToken' => [ 'shape' => 'String', ], ], ], 'ListTagsForResourceRequest' => [ 'type' => 'structure', 'required' => [ 'ResourceArn', ], 'members' => [ 'ResourceArn' => [ 'shape' => 'Arn', 'location' => 'uri', 'locationName' => 'ResourceArn', ], ], ], 'ListTagsForResourceResponse' => [ 'type' => 'structure', 'required' => [ 'ResourceArn', 'Tags', ], 'members' => [ 'ResourceArn' => [ 'shape' => 'Arn', ], 'Tags' => [ 'shape' => 'TagMap', ], ], ], 'MaxQueryResults' => [ 'type' => 'integer', 'max' => 100, 'min' => 0, ], 'Pages' => [ 'type' => 'list', 'member' => [ 'shape' => 'Url', ], 'max' => 50, 'min' => 0, ], 'PutRumEventsRequest' => [ 'type' => 'structure', 'required' => [ 'AppMonitorDetails', 'BatchId', 'Id', 'RumEvents', 'UserDetails', ], 'members' => [ 'AppMonitorDetails' => [ 'shape' => 'AppMonitorDetails', ], 'BatchId' => [ 'shape' => 'String', ], 'Id' => [ 'shape' => 'AppMonitorId', 'location' => 'uri', 'locationName' => 'Id', ], 'RumEvents' => [ 'shape' => 'RumEventList', ], 'UserDetails' => [ 'shape' => 'UserDetails', ], ], ], 'PutRumEventsResponse' => [ 'type' => 'structure', 'members' => [], ], 'QueryFilter' => [ 'type' => 'structure', 'members' => [ 'Name' => [ 'shape' => 'QueryFilterKey', ], 'Values' => [ 'shape' => 'QueryFilterValueList', ], ], ], 'QueryFilterKey' => [ 'type' => 'string', ], 'QueryFilterValue' => [ 'type' => 'string', ], 'QueryFilterValueList' => [ 'type' => 'list', 'member' => [ 'shape' => 'QueryFilterValue', ], ], 'QueryFilters' => [ 'type' => 'list', 'member' => [ 'shape' => 'QueryFilter', ], ], 'QueryTimestamp' => [ 'type' => 'long', ], 'ResourceNotFoundException' => [ 'type' => 'structure', 'required' => [ 'message', 'resourceName', ], 'members' => [ 'message' => [ 'shape' => 'String', ], 'resourceName' => [ 'shape' => 'String', ], 'resourceType' => [ 'shape' => 'String', ], ], 'error' => [ 'httpStatusCode' => 404, 'senderFault' => true, ], 'exception' => true, ], 'RumEvent' => [ 'type' => 'structure', 'required' => [ 'details', 'id', 'timestamp', 'type', ], 'members' => [ 'details' => [ 'shape' => 'JsonValue', 'jsonvalue' => true, ], 'id' => [ 'shape' => 'String', ], 'metadata' => [ 'shape' => 'JsonValue', 'jsonvalue' => true, ], 'timestamp' => [ 'shape' => 'Timestamp', ], 'type' => [ 'shape' => 'String', ], ], ], 'RumEventList' => [ 'type' => 'list', 'member' => [ 'shape' => 'RumEvent', ], ], 'ServiceQuotaExceededException' => [ 'type' => 'structure', 'required' => [ 'message', ], 'members' => [ 'message' => [ 'shape' => 'String', ], ], 'error' => [ 'httpStatusCode' => 402, 'senderFault' => true, ], 'exception' => true, ], 'SessionSampleRate' => [ 'type' => 'double', 'max' => 1, 'min' => 0, ], 'StateEnum' => [ 'type' => 'string', 'enum' => [ 'CREATED', 'DELETING', 'ACTIVE', ], ], 'String' => [ 'type' => 'string', ], 'TagKey' => [ 'type' => 'string', 'max' => 128, 'min' => 1, 'pattern' => '^(?!aws:)[a-zA-Z+-=._:/]+$', ], 'TagKeyList' => [ 'type' => 'list', 'member' => [ 'shape' => 'TagKey', ], 'max' => 50, 'min' => 0, ], 'TagMap' => [ 'type' => 'map', 'key' => [ 'shape' => 'TagKey', ], 'value' => [ 'shape' => 'TagValue', ], ], 'TagResourceRequest' => [ 'type' => 'structure', 'required' => [ 'ResourceArn', 'Tags', ], 'members' => [ 'ResourceArn' => [ 'shape' => 'Arn', 'location' => 'uri', 'locationName' => 'ResourceArn', ], 'Tags' => [ 'shape' => 'TagMap', ], ], ], 'TagResourceResponse' => [ 'type' => 'structure', 'members' => [], ], 'TagValue' => [ 'type' => 'string', 'max' => 256, 'min' => 0, ], 'Telemetries' => [ 'type' => 'list', 'member' => [ 'shape' => 'Telemetry', ], ], 'Telemetry' => [ 'type' => 'string', 'enum' => [ 'errors', 'performance', 'http', ], ], 'ThrottlingException' => [ 'type' => 'structure', 'required' => [ 'message', ], 'members' => [ 'message' => [ 'shape' => 'String', ], 'quotaCode' => [ 'shape' => 'String', ], 'retryAfterSeconds' => [ 'shape' => 'Integer', 'location' => 'header', 'locationName' => 'Retry-After', ], 'serviceCode' => [ 'shape' => 'String', ], ], 'error' => [ 'httpStatusCode' => 429, 'senderFault' => true, ], 'exception' => true, 'retryable' => [ 'throttling' => true, ], ], 'TimeRange' => [ 'type' => 'structure', 'required' => [ 'After', ], 'members' => [ 'After' => [ 'shape' => 'QueryTimestamp', ], 'Before' => [ 'shape' => 'QueryTimestamp', ], ], ], 'Timestamp' => [ 'type' => 'timestamp', ], 'Token' => [ 'type' => 'string', ], 'UntagResourceRequest' => [ 'type' => 'structure', 'required' => [ 'ResourceArn', 'TagKeys', ], 'members' => [ 'ResourceArn' => [ 'shape' => 'Arn', 'location' => 'uri', 'locationName' => 'ResourceArn', ], 'TagKeys' => [ 'shape' => 'TagKeyList', 'location' => 'querystring', 'locationName' => 'tagKeys', ], ], ], 'UntagResourceResponse' => [ 'type' => 'structure', 'members' => [], ], 'UpdateAppMonitorRequest' => [ 'type' => 'structure', 'required' => [ 'Name', ], 'members' => [ 'AppMonitorConfiguration' => [ 'shape' => 'AppMonitorConfiguration', ], 'CwLogEnabled' => [ 'shape' => 'Boolean', ], 'Domain' => [ 'shape' => 'AppMonitorDomain', ], 'Name' => [ 'shape' => 'AppMonitorName', 'location' => 'uri', 'locationName' => 'Name', ], ], ], 'UpdateAppMonitorResponse' => [ 'type' => 'structure', 'members' => [], ], 'Url' => [ 'type' => 'string', 'max' => 1260, 'min' => 1, 'pattern' => 'https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)', ], 'UserDetails' => [ 'type' => 'structure', 'members' => [ 'sessionId' => [ 'shape' => 'String', ], 'userId' => [ 'shape' => 'String', ], ], ], 'ValidationException' => [ 'type' => 'structure', 'required' => [ 'message', ], 'members' => [ 'message' => [ 'shape' => 'String', ], ], 'error' => [ 'httpStatusCode' => 400, 'senderFault' => true, ], 'exception' => true, ], ],];
