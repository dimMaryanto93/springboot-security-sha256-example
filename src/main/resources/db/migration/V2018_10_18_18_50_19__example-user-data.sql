insert into user_detail(
    id,
    username,
    encript_password,
    hash_id,
    enabled,
    blocked,
    expired,
    last_login,
    created_by,
    created_date,
    last_updated_by,
    last_udpated_date)
values
  (uuid(), 'admin',  'eb63232d34ee1afa03a6a682488aef4d517cacd84ddf9a99d3458b460f0e04cb', '123456',  true, false, false, null, 'migration', now(), null, null),
  (uuid(), 'user_lock',  'eb63232d34ee1afa03a6a682488aef4d517cacd84ddf9a99d3458b460f0e04cb', '123456',  false, false, true, null, 'migration', now(), null, null),
  (uuid(), 'user_expired',  'eb63232d34ee1afa03a6a682488aef4d517cacd84ddf9a99d3458b460f0e04cb', '123456',  false, false, true , null, 'migration', now(), null, null),
  (uuid(), 'user_blocked',  'eb63232d34ee1afa03a6a682488aef4d517cacd84ddf9a99d3458b460f0e04cb', '123456',  true, true, false,  null, 'migration', now(), null, null);